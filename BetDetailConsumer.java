import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

public class BetDetailConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "bet_detail_consumer_group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Collections.singletonList("bet_detail"));

		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				String message = record.value();
				if (message.contains("\"returns\":")) {
					String[] parts = message.split(",");
					for (String part : parts) {
						if (part.contains("\"returns\":")) {
							String returnsString = part.split(":")[1].trim();
							Double returns = Double.parseDouble(returnsString);
							if (returns >= 1500.00) {
								System.out.println("Notification: returns amount is " + returns);
							}
							break;
						}
					}
				}
			}
		}
	}
}
