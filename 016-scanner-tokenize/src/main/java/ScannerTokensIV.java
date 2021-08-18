import java.util.Locale;
import java.util.Scanner;

public class ScannerTokensIV {

	public static void main(String[] args) {
		String advocates = """
				Billy,Korando,NA,2.600
				David,Delabasse,EMEA,8.522
				Denys,Makogon,EMEA,233
				José,Paumard,EMEA,6.131
				Nicolai,Parlog,EMEA,12.400
				""";

		try (Scanner scanner = new Scanner(advocates).useDelimiter("[,]+").useLocale(Locale.US)) {
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					System.out.println(scanner.nextInt() * 2);
				} else {
					System.out.println(scanner.next());
				}
			}
		}
		
		try (Scanner scanner = new Scanner(advocates).useDelimiter("[,\n]+").useLocale(Locale.GERMANY)) {
			while (scanner.hasNext()) {
				if (scanner.hasNextInt()) {
					System.out.println(scanner.nextInt() * 2);
				} else {
					System.out.println(scanner.next());
				}
			}
		}
	}

}
