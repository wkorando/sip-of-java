# Predicate.not

## Post-Java 11 Syntax
```java
Stream<Employee> streamOfEmployees = 
		Stream.of(new Employee("Homer", 187, false),
		new Employee("Lenny", 122, false), 
		new Employee("Carl", 93, false),
		new Employee("Montgomery", 552, true));

streamOfEmployees.filter(Predicate.not(Employee::executive))
	.forEach(System.out::println);
```

## Pre-Java 11 Syntax
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