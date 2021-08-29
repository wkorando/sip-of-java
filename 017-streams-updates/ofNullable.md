# Stream.ofNullable

```java
record Employee(String name, 
	int monthsEmployed, boolean executive) {}

public static void main(String[] args) {
	List<Employee> streamOfEmployees = null;
	System.out.println("streamOfEmployees is null");
	Stream.ofNullable(streamOfEmployees).
	flatMap(Collection::stream).forEach(System.out::println);

	streamOfEmployees = List.of(new Employee("Homer", 187, false),
	new Employee("Lenny", 122, false), 
	new Employee("Carl", 93, false),
	new Employee("Montgomery", 552, true));
	
	System.out.println("streamOfEmployees is not null");
	Stream.ofNullable(streamOfEmployees).
	flatMap(Collection::stream).forEach(System.out::println);
}
```

## Output

```
streamOfEmployees is null
streamOfEmployees is not null
Employee[name=Homer, monthsEmployed=187, executive=false]
Employee[name=Lenny, monthsEmployed=122, executive=false]
Employee[name=Carl, monthsEmployed=93, executive=false]
Employee[name=Montgomery, monthsEmployed=552, executive=true]
```