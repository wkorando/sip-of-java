import java.util.Scanner;

public class ScannerTokensII {

	public static void main(String[] args) {
		String advocates = """
				Billy,Korando,NA,2600
				David,Delabassée,EMEA,8522
				Denys,Makogon,EMEA,233
				José,Paumard,EMEA,6131
				Nicolai,Parlog,EMEA,12400
				""";

		try (Scanner scanner = new Scanner(advocates).useDelimiter("[,\n]+")) {
			while (scanner.hasNext()) {
				System.out.println(scanner.next());
			}
		}
	}

}
