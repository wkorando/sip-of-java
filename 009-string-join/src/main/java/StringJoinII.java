import java.util.List;
import java.util.StringJoiner;

public class StringJoinII {

	public static void main(String[] args) {
		
		List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
		StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
		for(String devAdvocate : devAdvocates) {
			joiner.add(devAdvocate);
		}
		
		System.out.println(joiner.toString());
	}

}
