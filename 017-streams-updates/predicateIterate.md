# Stream.iterate with Predicate

```java
System.out.println("Remaining days of the week:");
Stream.iterate(
		LocalDate.now().getDayOfWeek().getValue(), 
		d -> d <= DayOfWeek.SUNDAY.getValue(), 
		d -> ++d)
		.forEach(
		d -> System.out.println(DayOfWeek.of(d)));
```

## Output

```
Remaining days of the week:
FRIDAY
SATURDAY
SUNDAY
```