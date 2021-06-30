import java.util.List;

public class StringJoinV {

	public static void main(String[] args) {
		
		List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
		System.out.println(String.join(", ", devAdvocates));
	}

}
