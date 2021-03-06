# Streams Post Java 8 Updates

[Code](https://github.com/wkorando/sip-of-java/tree/main/017-streams-updates) - [Video](https://www.youtube.com/watch?v=D6C600mDZ0Y) - [Script](https://github.com/wkorando/sip-of-java/blob/main/017-streams-updates/script.srt)

Since their initial release with Java 8, Streams, and supporting classes including; Predicate and Collectors, have continued to see updates improving usability and helping to fill in functionality gaps. Let's take a look at a few key changes.

## Collectors.teeing

The teeing method was added to `Collectors` with Java 12. Teeing allows the merging of the results of two down stream collectors. Teeing can be useful from when processing a stream and needing two discrete types of values from it. In the below example the minimum and maximum value from a stream is being retrieved and put into a list. 

```java
record Employee(String name, 
	int monthsEmployed, boolean executive) {}

public static void main(String[] args) {

	Stream<Employee> streamOfEmployees = Stream.of(
		new Employee("Homer", 187, false),
		new Employee("Lenny", 122, false),
		new Employee("Carl", 93, false),
		new Employee("Montgomery", 552, true));

	streamOfEmployees.collect(
			Collectors.teeing(
			Collectors.maxBy(
				Comparator.comparing(Employee::monthsEmployed)),
			Collectors.minBy(
				Comparator.comparing(Employee::monthsEmployed)), 
            (e1, e2) -> {
                return List.of(e1.get(), e2.get());
            }
			)).forEach(System.out::println);
}
```

### Output

```
Employee[name=Montgomery, monthsEmployed=552, executive=true]
Employee[name=Carl, monthsEmployed=93, executive=false]
```

## Predicate.not()

The `public static not(Predicate<T>)` was added to `Predicate` with Java 11. This allows for a more concise definition of a negation predicate than what was available before Java 11. Below is an example of using `not()` with Java 11: 

### Java 11 Syntax
```java
Stream<Employee> streamOfEmployees = 
		Stream.of(new Employee("Homer", 187, false),
		new Employee("Lenny", 122, false), 
		new Employee("Carl", 93, false),
		new Employee("Montgomery", 552, true));

streamOfEmployees.filter(Predicate.not(Employee::executive))
	.forEach(System.out::println);
```
And what the equivalent functionality would like like pre-Java 11:

### Pre-Java 11 Syntax
```java
Stream<Employee> streamOfEmployees = 
		Stream.of(new Employee("Homer", 187, false),
		new Employee("Lenny", 122, false), 
		new Employee("Carl", 93, false),
		new Employee("Montgomery", 552, true));
Predicate<Employee> isExecutive = Employee::executive;

streamOfEmployees.filter(isExecutive.negate())
	.forEach(System.out::println);
```

## Stream.ofNullable()

Nulls are a source of frequent headaches to Java developers. Added in Java 9, `Stream.ofNullable` provides for null safety when processing a single element as a `Stream`. If the passed in value is null an empty `Stream` is returned. If the value is non-null the passed in value is wrapped as a single element stream. Below is an example of using a `ofNullable` to handle a potentially null `List`:

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

### Output

```
streamOfEmployees is null
streamOfEmployees is not null
Employee[name=Homer, monthsEmployed=187, executive=false]
Employee[name=Lenny, monthsEmployed=122, executive=false]
Employee[name=Carl, monthsEmployed=93, executive=false]
Employee[name=Montgomery, monthsEmployed=552, executive=true]
```

## Stream.iterate with Predicate

Also in Java 9 an additional `Stream.iterate` was added that takes a `Predicate`. The `Predicate` behaves as a "hasNext" and is processed before the "next" which is a `UnaryOperator<T>`:

```java
System.out.println("Remaining days of the week:");
Stream.iterate(
		LocalDate.now().getDayOfWeek().getValue(), 
		d -> d <= DayOfWeek.SUNDAY.getValue(), 
		d -> ++d)
		.forEach(
		d -> System.out.println(DayOfWeek.of(d)));
```

### Output

```
Remaining days of the week:
FRIDAY
SATURDAY
SUNDAY
```

## Further Reading

Predicate Javadoc: [https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/function/Predicate.html](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/function/Predicate.html)

Stream Javadoc: [https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Stream.html](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Stream.html)

Collectors Javadoc: [https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Collectors.html](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Collectors.html)

Happy Coding!