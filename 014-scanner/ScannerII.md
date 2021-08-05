```java
String wordsAndNumbers = """
		Longing rusted furnace
		daybreak 17 benign 
		9 homecoming 1 
		freight car
		""";

try (Scanner scanner = new Scanner(wordsAndNumbers)) {
	scanner.findAll("[A-Za-z']+")
		.map(MatchResult::group)
		.forEach(System.out::println);
}
```

## Output:

```
Longing
rusted
furnace
daybreak
benign
homecoming
freight
car
```