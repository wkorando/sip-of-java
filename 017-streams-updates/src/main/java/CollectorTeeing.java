import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorTeeing {
	record Employee(String name, int monthsEmployed, boolean executive) {
	}

	public static void main(String[] args) {

		Stream<Employee> streamOfEmployees = Stream.of(new Employee("Homer", 187, false),
				new Employee("Lenny", 122, false), new Employee("Carl", 93, false),
				new Employee("Montgomery", 552, true));

		streamOfEmployees.collect(
				Collectors.teeing(
				Collectors.maxBy(Comparator.comparing(Employee::monthsEmployed)),
				Collectors.minBy(Comparator.comparing(Employee::monthsEmployed)), 
                (e1, e2) -> {
                    return List.of(e1.get(), e2.get());
                }
				)).forEach(System.out::println);
	}

}
