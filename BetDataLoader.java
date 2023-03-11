import java.io.*;
import java.net.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class BetDataLoader {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void load(String fileName) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		List<BetData> betDataList = objectMapper.readValue(new File(fileName), new TypeReference<List<BetData>>() {});

		for (BetData betData : betDataList) {
			insertBetData(betData);
			kafkaTemplate.send("bet_detail", objectMapper.writeValueAsString(betData));
		}
	}

	private void insertBetData(BetData betData) {
		String sql = "INSERT INTO betting_summary (numbets, game, stake, returns, clientid, date) VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, betData.getNumbets(), betData.getGame(), betData.getStake(), betData.getReturns(), betData.getClientid(), betData.getDate());
	}
}

public class BetData {

	private int id;
	private int numbets;
	private String game;
	private BigDecimal stake;
	private BigDecimal returns;
	private int clientid;
	private LocalDate date;

	// getters and setters
}
