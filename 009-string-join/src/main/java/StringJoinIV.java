import java.util.List;
import java.util.stream.Collectors;

public class StringJoinIV {

	public static void main(String[] args) {
		
		List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
		String devAdvocatesList = devAdvocates.stream().collect(Collectors.joining(", ", "{ ", " }"));
		
		System.out.println(devAdvocatesList);
	}

}
