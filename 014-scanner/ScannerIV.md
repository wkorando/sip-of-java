```java
String wordsAndNumbers = """
		Longing rusted furnace
		daybreak 17 benign
		9 homecoming 1
		freight car
		""";

try (Scanner scanner = new Scanner(wordsAndNumbers)) {
	while (scanner.hasNextLine()) {
		String result = scanner.findInLine(
			Pattern.compile("[A-Za-z']+"));
			// Find first match in line

		if (result != null) {
			System.out.println(result);
		}

		scanner.nextLine();// Returns any skipped 
						   //over input as a String
	}
}
```

## Output:

```
Longing
daybreak
homecoming
freight
```