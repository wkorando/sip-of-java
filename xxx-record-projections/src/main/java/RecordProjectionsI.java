import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecordProjectionsI {
	record Person(String firstName, String lastName, String middleName, String title, Integer id) {
	}

	public static void main(String[] args) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();

		for (int i = 0; i < 100000; i++) {
			mapper.writeValueAsString(new Person("Billy", "Korando", "Michael", "Java Developer Advocate", 1));
			mapper.readValue(
					"""
							{"firstName":"Billy","lastName":"Korando","middleName":"Michael","title":"Java Developer Advocate","id":1}
														""",
					Person.class);
		}
	}

}
