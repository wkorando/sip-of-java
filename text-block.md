# Text Block

Text Blocks, added in Java 15 ([JEP 378](https://openjdk.java.net/jeps/378)), are a Java feature designed to improve the experience of working with large and formatted strings in Java. 

## Working with JSON in a "one-dimensional" String

```java
String simpleJSONMessage = "{\n" + //
		"\t\"firstName\": \"Billy\",\n" + //
		"\t\"lastName\": \"Korando\",\n" + //
		"\t\"jobTitle\": \"Java Developer Advocate\",\n" + //
		"\t\"twitterHandle\": \"@BillyKorando\"\n" + //
		"}";
```

Output:

```
{
        "firstName": "Billy",
        "lastName": "Korando",
        "jobTitle": "Java Developer Advocate",
        "twitterHandle": "@BillyKorando"
}
```

## Working with JSON in a Text Block

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

```
{
        "firstName": "Billy",
        "lastName": "Korando",
        "jobTitle": "Java Developer Advocate",
        "twitterHandle": "@BillyKorando"
}
```

### Incidental Whitespace:

## Using String Formatter

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

```
String aReallyLongLine = """
		Lorem ipsum dolor sit amet, consectetur adipiscing elit, \
		sed do eiusmod tempor incididunt ut labore et dolore \
		magna aliqua. \
		""";

System.out.println(aReallyLongLine);
```

Output:

```
Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
```

 
