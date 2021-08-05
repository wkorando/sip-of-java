import java.util.Scanner;
import java.util.regex.Pattern;

public class ScannerV {

	public static void main(String... args) {
		String wordsAndNumbers = """
				Longing rusted furnace
				daybreak 17 benign
				9 homecoming 1
				freight car
				""";

		try (Scanner scanner = new Scanner(wordsAndNumbers)) {
			while (scanner.hasNextLine()) {
				String result = scanner.findInLine(Pattern.compile("[A-Za-z']+"));// Find first match in line
				if (result != null) {
					System.out.print(result + " ");
				}

				String result2 = scanner.findInLine(Pattern.compile("[A-Za-z']+"));// Find second match in line
				if (result2 != null) {
					System.out.print(result2);
				}

				System.out.println();
				scanner.nextLine();// Returns any skipped over input as a String
			}
		}
	}
}
