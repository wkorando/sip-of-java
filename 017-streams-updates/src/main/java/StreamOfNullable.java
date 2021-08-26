import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class StreamOfNullable {
	record Employee(String name, int monthsEmployed, boolean executive) {
	}

	public static void main(String[] args) {
		List<Employee> streamOfEmployees = null;
		System.out.println("streamOfEmployees is null");
		Stream.ofNullable(streamOfEmployees).flatMap(Collection::stream).forEach(System.out::println);

		streamOfEmployees = List.of(new Employee("Homer", 187, false),
		new Employee("Lenny", 122, false), 
		new Employee("Carl", 93, false),
		new Employee("Montgomery", 552, true));
		
		System.out.println("streamOfEmployees is not null");
		Stream.ofNullable(streamOfEmployees).flatMap(Collection::stream).forEach(System.out::println);
	}
}