import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsUpdate {

	public static void main(String[] args) throws Exception {
		System.out.println("How to stream the letters of a string");
		String verse = "Isaac Newton";
		IntStream letters = verse.chars();
		System.out.println("The JDK 8 basic and unexpected way");
		letters.forEach(letter -> System.out.print(letter + " "));
		// JDK 8
		Stream<Character> lettersAsChars = verse.chars().mapToObj(letter -> (char) letter);
		System.out.println("\nThe JDK 8 character way (do not remove the cast, or forget to call mapToObj!)");
		lettersAsChars.forEach(letter -> System.out.print(letter + " "));
		// JDK 8
		Stream<String> lettersAsString = verse.chars().mapToObj(letter -> (char) letter).map(Object::toString);
		System.out.println("\nThe JDK 8 string, unreadable and painful way");
		lettersAsString.forEach(letter -> System.out.print(letter + " "));
		// JDK 11
		Stream<String> lettersAsString2 = verse.chars().mapToObj(Character::toString);
		System.out.println("\nThe JDK 11 string, compact way");
		lettersAsString2.forEach(letter -> System.out.print(letter + " "));
	}

}
