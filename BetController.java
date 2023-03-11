import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

// import statements
@Controller
public class BetController {

	@Autowired
	private BetRepository betRepository;

	@GetMapping("/")
	public String showSearchForm(Model model) {
		model.addAttribute("bet", new Bet());
		return "searchForm";
	}

	@PostMapping("/")
	public String search(@ModelAttribute Bet bet, Model model) {
		List<Bet> bets = betRepository.findByGameAndClientIdAndDate(bet.getGame(), bet.getClientId(), bet.getDate());
		model.addAttribute("bets", bets);
		return "searchResults";
	}
}

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

	List<Bet> findByGameAndClientIdAndDate(String game, String clientId, LocalDate date);

}
