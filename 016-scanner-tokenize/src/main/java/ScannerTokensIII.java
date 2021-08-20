import java.util.Scanner;

public class ScannerTokensIII {

	public static void main(String[] args) {
		String advocates = """
				Billy,Korando,NA,2600
				David,Delabasse,EMEA,8522
				Denys,Makogon,EMEA,233
				José,Paumard,EMEA,6131
				Nicolai,Parlog,EMEA,12400
				""";

		try (Scanner scanner = new Scanner(advocates).useDelimiter("[,\n]+")) {
			int i = 0;
			while (scanner.hasNext()) {
				i++;
				if (scanner.hasNextInt()) {
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
