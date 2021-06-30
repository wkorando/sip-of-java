import java.util.List;
import java.util.StringJoiner;

public class StringJoinIII {

	public static void main(String[] args) {
		
		List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
		StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
		devAdvocates.forEach(joiner::add);
		
		System.out.println(joiner.toString());
	}

}
