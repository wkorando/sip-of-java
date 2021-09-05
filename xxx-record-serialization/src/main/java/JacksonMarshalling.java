import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMarshalling {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		record Point(int x, int y) {}
		System.out.println(mapper.writeValueAsString(new Point(1, 2)));
	}
}
