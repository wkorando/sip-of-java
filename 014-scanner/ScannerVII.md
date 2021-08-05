```java
// Nine space buffer between end of longest word
// and start of next "column"
String formattedWordsAndNumbers = """
		Longing          rusted furnace
		daybreak         benign 17
		9                homecoming 1
		freight          car
		""";

try (Scanner scanner = new Scanner(formattedWordsAndNumbers)) {
	while (scanner.hasNextLine()) {
		String result = scanner.findWithinHorizon(
		Pattern.compile("[A-Za-z']+"), 10); 
		// Find match within first 10 characters of line
		if (result != null) {
			System.out.print(result + " ");
		}
		String result2 = scanner.findWithinHorizon(
		Pattern.compile("[A-Za-z']+"), 10); 
		// Find match within next 10 characters of line
		
		if (result2 != null) {
			System.out.print(result2);
		}
		System.out.println();
		scanner.nextLine();// Returns any skipped 
		                   // over input as a String
	}
}
```

## Output:

```
Longing 
daybreak b

freight 
```