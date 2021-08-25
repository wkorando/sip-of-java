import java.util.stream.Stream;

public class StreamOfNullable {
	record Employee(String name, int monthsEmployed, boolean executive) {
	}

	public static void main(String[] args) {

		System.out.println("nullableEmployee(false)");
		Stream.ofNullable(nullableEmployee(false)).forEach(System.out::println);
		System.out.println("nullableEmployee(true)");
		Stream.ofNullable(nullableEmployee(true)).forEach(System.out::println);
	}

	private static Employee nullableEmployee(boolean returnEmployee) {

		if (returnEmployee) {
			return new Employee("Homer", 187, false);
		} else {
			return null;
		}
	}
}
