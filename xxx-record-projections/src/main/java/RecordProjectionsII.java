import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RecordProjectionsII {
	static class Person {
		private String firstName;
		private String lastName;
		private String middleName;
		private String title;
		private Integer id;

		public Person() {
		}

		public Person(String firstName, String lastName, String middleName, String title, Integer id) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.middleName = middleName;
			this.title = title;
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getMiddleName() {
			return middleName;
		}

		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

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
