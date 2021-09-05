import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonExample {
	private record InnerPoint(int x, int y) {}
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		record LocalPoint(int x, int y) {}
		
		//Top Level Record
		String json = mapper.writeValueAsString(new TopLevelPoint(1, 2));
		System.out.println(json);
		System.out.println(mapper.readValue(json, TopLevelPoint.class));
		
		//Inner Record
		json = mapper.writeValueAsString(new InnerPoint(1, 2));
		System.out.println(json);
		System.out.println(mapper.readValue(json, InnerPoint.class));
		
		//Local Record
		json = mapper.writeValueAsString(new LocalPoint(1, 2));
		System.out.println(json);
		System.out.println(mapper.readValue(json, LocalPoint.class));
	}
}