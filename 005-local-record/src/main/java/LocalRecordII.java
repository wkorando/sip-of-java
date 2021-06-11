import java.util.stream.Stream;

public class LocalRecordII {

	public static void main(String[] args) {

		String firstName1 = "Billy";
		String lastName1 = "Korando";
		String title1 = "Java Developer Advocate";
		String twitterHandle1 = "@BillyKorando";
		
		String firstName2 = "Sharat";
		String lastName2 = "Chander";
		String title2 = "Java Developer Advocate";
		String twitterHandle2 = "@Sharat_Chander";
		
		record Person(String firstName, String lastName, String title, String twitterHandle) {}
		
		var persons = Stream.of(new Person(firstName1, lastName1, title1, twitterHandle1),
				new Person(firstName2, lastName2, title2, twitterHandle2));

		persons.forEach(System.out::println);
	}

}
