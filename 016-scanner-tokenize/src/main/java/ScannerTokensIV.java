import java.util.Locale;
import java.util.Scanner;

public class ScannerTokensIV {

	public static void main(String[] args) {
		String advocates = """
				Billy,Korando,NA,2.600
				David,Delabassée,EMEA,8.522
				Denys,Makogon,EMEA,233
				José,Paumard,EMEA,6.131
				Nicolai,Parlog,EMEA,12.400
				""";
		System.out.println("US Locale\n");
		try (Scanner scanner = new Scanner(advocates).useDelimiter("[,\n]+").useLocale(Locale.US)) {
			int i = 0;
			while (scanner.hasNext()) {
				i++;
				if (scanner.hasNextInt()) {// Will always be false because "."
											// is only used as a decimal in US
					System.out.print(scanner.nextInt() * 2);
				} else {
					System.out.print(scanner.next() + " ");
				}
				if (i % 4 == 0) {
					System.out.print("\n");
				}
			}
		}
		System.out.println("\nGermany Locale\n");
		try (Scanner scanner = new Scanner(advocates).useDelimiter("[,\n]+").useLocale(Locale.GERMANY)) {
			int i = 0;
			while (scanner.hasNext()) {
				i++;
				if (scanner.hasNextInt()) {// Will return true for last item,
											// as "." is used like "," in Germany
					System.out.print(scanner.nextInt() * 2);
				} else {
					System.out.print(scanner.next() + " ");
				}

				if (i % 4 == 0) {
					System.out.print("\n");
				}
			}
		}
	}
}
