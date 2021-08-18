import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Teeing {
	interface Regional {
		String region();
	}

	public static void main(String[] args) {
		
		record Advocate(String name, String region) implements Regional {};
		record Conference(String name, String region) implements Regional  {};
		Predicate<Regional> predicate = r -> r.region().equals("NA");
		Predicate<Conference> predicate2 = c -> c.name().equals("NA");
		List<Advocate> advocates = List.of(new Advocate("Billy Korando", "NA"), new Advocate("Nicolai Parlog", "EMEA"));
		List<Conference> conferences = List.of(new Conference("KCDC", "NA"), new Conference("Devoxx", "EMEA"));
		
		Collector<Conference, ?, Object> advocatesAndConferences = 
				Collectors.teeing(
						Collectors.filtering(predicate, Collectors.toList()),
						Collectors.filtering(predicate2,  Collectors.toList()), 
						(a, c) -> 
						Map.ofEntries(Map.entry("advocates", a), 
						Map.entry("conferences", c)));
		conferences.stream().collect(advocatesAndConferences);
	}
}
