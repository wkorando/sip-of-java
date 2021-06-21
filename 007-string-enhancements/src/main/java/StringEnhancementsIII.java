import java.util.stream.Stream;

public class StringEnhancementsIII {

	public static void main(String[] args) {

		String stringWithManyLines = """
				There once was a language.
				Which improved how it handled Strings.
				That language?
				Java!
				""";
		Stream<String> lines = stringWithManyLines.lines();
		
		lines.forEach(System.out::print);//print as a single line
	}

}
