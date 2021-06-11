# Text Block

Text Blocks, added in Java 15 ([JEP 378](https://openjdk.java.net/jeps/378)), are a Java feature designed to improve the experience of working with large and formatted strings in Java. As is commonly the case when working with markup languages like; JSON, XML, and HTML in Java code.  

## Working with JSON in a "one-dimensional" String

Historically String literals in Java could only be defined "one-dimensionally" and required escape sequences for line breaks and escaping characters like `"`. Which made even defining even simple JSON messages like in the example below near illegible: 

```
String simpleJSONMessage = "{\n" + //
		"\t\"firstName\": \"Billy\",\n" + //
		"\t\"lastName\": \"Korando\",\n" + //
		"\t\"jobTitle\": \"Java Developer Advocate\",\n" + //
		"\t\"twitterHandle\": \"@BillyKorando\"\n" + //
		"}";
```

## Working with JSON in a Text Block

A Text Block is delimited by triple-quotes: `"""`. Within the triple quotes, formatting and line breaks are preserved and the need for escaping is relaxed. Allowing the above JSON message to be defined in the same way it would be printed:

```
String simpleJSONMessage = """
		{
		        "firstName": "Billy",
		        "lastName": "Korando",
		        "jobTitle": "Java Developer Advocate",
		        "twitterHandle": "@BillyKorando"
		}
		""";
```

### Incidental Whitespace:

Within text blocks is the concept of incidental whitespace. You can read more about the topic [here](https://cr.openjdk.java.net/~jlaskey/Strings/TextBlocksGuide_v9.html#incidental-white-space), which covers all the edge cases. But the short way of describing it is the compiler will ignore the whitespace, both tabs and spaces, within your code, but not within the text block itself. 

## Using String Formatter

Text Blocks are fully compatible with the String [Formatter](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Formatter.html) which can allow for values to be easily inserted in within a text block like in the below example:

```
String simpleJSONMessage = """
		{
		        "firstName": "%s",
		        "lastName": "%s",
		        "jobTitle": "%s",
		        "twitterHandle": "%s"
		}
		""";

System.out.println(simpleJSONMessage.
		formatted("Billy", 
				"Korando", 
				"Java Developer Advocate", 
				"@BillyKorando"));
```

## Really Long Single-Line Strings

Text blocks can also make working with really long strings that are to be printed as a single line easier as well. Simply adding a `\` at the end of the line suppresses a new line character allow ing the below String:

```
String aReallyLongLine = """
		Lorem ipsum dolor sit amet, consectetur adipiscing elit, \
		sed do eiusmod tempor incididunt ut labore et dolore \
		magna aliqua. \
		""";

System.out.println(aReallyLongLine);
```

To be printed as a single line:

```
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
```

 Happy coding!
