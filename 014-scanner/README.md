# Scanner API - Searching Input

The Scanner API, initially introduced in JDK 5, and having received updates in subsequent JDK releases provides some key benefits when search an input source for specific content. Scanner also has a second function of tokenizing an input source, but that will be covered in a future Sip. 

## Scanner Performance Overview

Scanner's key benefit comes from it's low overhead stemming from three key characteristics:

* Handles I/O
* Buffered
* Discards unmatched input

This allows Scanner, when the input source has reasonable line breaks, to process even a very large input source while consuming minimal system resources. 

## Using Scanner

### findAll()

Most developers when using the search function with in Scanner will likely interact with `findAll()` which will return a `Stream` of `MatchResult`. There are several different ways to use `findAll`.

#### Search By String Literal

Matching on a String literal like in this example: 

```java
String wordsAndNumbers = """
		Longing rusted furnace
		daybreak 17 benign 
		9 homecoming 1 
		freight car
		""";

try (Scanner scanner = new Scanner(wordsAndNumbers)) {
	scanner.findAll("benign")
		.map(MatchResult::group)
		.forEach(System.out::println);
}
```

Which would return this:

```
benign
```
#### Search By Regex

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

Which would return this:

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

#### Search By Pattern

A compile Pattern instance can also be passed in, which produces identical results to the above example:

```java
String wordsAndNumbers = """
		Longing rusted furnace
		daybreak 17 benign 
		9 homecoming 1 
		freight car
		""";

try (Scanner scanner = new Scanner(wordsAndNumbers)) {
	scanner.findAll(Pattern.compile("[A-Za-z']+"))
		.map(MatchResult::group)
		.forEach(System.out::println);
}
```


### findByLine()

For more precise control there is `findByLine()`, which returns the first match in a line to the passed in pattern. 

To move through an input source there is `hasNextLine()` and `nextLine()`, which is similar to the `Iterator` class. 

`nextLine()` will return any skipped input as a String. 

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
			Pattern.compile("[A-Za-z']+")); // Find first match in line

		if (result != null) {
			System.out.println(result);
		}

		scanner.nextLine(); // Returns any skipped 
			             //over input as a String
	}
}
```

This example would print out the following:

```
Longing
daybreak
homecoming
freight
```

To scan for more results, `findByLine()` can be called multiple times, like in this example:

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
			System.out.print(result + " ");
		}

		String result2 = scanner.findInLine(
			Pattern.compile("[A-Za-z']+"));
			// Find second match in line
		if (result2 != null) {
			System.out.print(result2);
		}

		System.out.println();
		scanner.nextLine();// Returns any skipped 
						   //over input as a String
	}
}
```

Which returns the following:

```
Longing rusted
daybreak benign
homecoming 
freight car
```

### findWithInHorizon()

There is also `findWithinHorizon()` which can be configured to scan a specific number of characters. 

```java
// Nine space buffer between end of longest word
// and start of next "column"
String formattedWordsAndNumbers = """
		Longing          rusted furnace
		daybreak         benign 17
		9                homecoming 1
		freight          car
		""";

try (Scanner scanner 
	= new Scanner(formattedWordsAndNumbers)) {
	while (scanner.hasNextLine()) {
		String result = scanner.findWithinHorizon(
		Pattern.compile("[A-Za-z']+"), 10); 
		// Find match within first 10 characters of line
		
		if (result != null) {
			System.out.println(result);
		}
		scanner.nextLine();// Returns any skipped 
		                   //over input as a String
	}
}
```

If `findWithinHorizon()` does not match within its search area, the cursor will not advance. In this example the the search area (the horizon) is nine characters, so calling `findWithinHorizon()` multiple times on the first and fourth lines does not return any additional results, but does on the second line:

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

The output, not no additional return after longing, the "b" being return after "daybreak" and also no return after "freight": 


```
Longing 
daybreak b

freight 
```

## Extra Learning

Be sure to check out Stuart Marks great article on Scanner: [https://stuartmarks.wordpress.com/2020/04/14/scanner-is-a-weird-but-useful-beast/](https://stuartmarks.wordpress.com/2020/04/14/scanner-is-a-weird-but-useful-beast/)

Scanner class JDK 16 JavaDoc: [https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Scanner.html](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Scanner.html)

Happy Coding!