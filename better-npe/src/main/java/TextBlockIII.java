
public class TextBlockIII {

	public static void main(String[] args) {

		String simpleJSONMessage = """
				{
				        "firstName": "%s",
				        "lastName": "%s",
				        "jobTitle": "%s",
				        "twitterHandle": "%s"
				}
				""";

		System.out.println(simpleJSONMessage.
				formatted("Billy", 
						"Korando", 
						"Java Developer Advocate", 
						"@BillyKorando"));
		
	}

}
