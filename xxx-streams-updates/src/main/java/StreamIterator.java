import java.util.stream.Stream;

public class StreamIterator {
	record Employee(String name, int monthsEmployed, boolean executive) {}

	public static void main(String[] args) {

		Stream.iterate(1, i -> i < 20, i -> i*2).forEach(System.out::println);
	}

}