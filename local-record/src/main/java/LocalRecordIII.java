import java.util.Set;

public class LocalRecordIII {

	public static void main(String[] args) {

		String firstName1 = "Billy";
		String lastName1 = "Korando";
		String title1 = "Java Developer Advocate";
		String twitterHandle1 = "@BillyKorando";

		String firstName2 = "Billy";
		String lastName2 = "Korando";
		String title2 = "Java Developer Advocate";
		String twitterHandle2 = "@BillyKorando";

		record Person(String firstName, String lastName, String title, String twitterHandle) {}

		var persons = Set.of(new Person(firstName1, lastName1, title1, twitterHandle1),
				new Person(firstName2, lastName2, title2, twitterHandle2));
	}

}
