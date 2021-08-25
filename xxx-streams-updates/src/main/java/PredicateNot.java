import java.util.function.Predicate;
import java.util.stream.Stream;

public class PredicateNot {
	record Employee(String name, int monthsEmployed, boolean executive) {}
	
	public static void main(String[] args) {
		Stream<Employee> streamOfEmployees = Stream.of(new Employee("Homer", 187, false),
				new Employee("Lenny", 122, false), new Employee("Carl", 93, false),
				new Employee("Montgomery", 552, true));
		Predicate<Employee> isExecutive = Employee::executive;
		streamOfEmployees.filter(Predicate.not(isExecutive)).forEach(System.out::println);
	}

}
