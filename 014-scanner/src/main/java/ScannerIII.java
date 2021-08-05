import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ScannerIII {

	public static void main(String... args) {
		String wordsAndNumbers = """
				Longing rusted furnace
				daybreak 17 benign 
				9 homecoming 1 
				freight car
				""";

		try (Scanner scanner = new Scanner(wordsAndNumbers)) {
			scanner.findAll(Pattern.compile("[A-Za-z']+")).map(MatchResult::group).forEach(System.out::println);
		}
	}
}
