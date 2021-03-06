# New Convenience Methods for String 

String is one of the most frequently used types within Java. With nearly every release of Java, String sees some changes, often in the form of adding convenience methods addressing common use cases when interacting with String instances. In this article we will example a few of these methods that have been added to String in recent release of Java. 


## isBlank

Needing to see if a String contains only whitespace characters is a common requirement. `isBlank()` added in Java 11, returns true if a String contains on whitespace as defined within `java.lang.Character.isWhitespace(int codePoint)`: 

```java
String aEmptyString = "";
if(aEmptyString.isEmpty()){
	System.out.println(
	"aEmptyString is empty!");
}
	
String aBlankString = "            ";
if(aBlankString.isBlank()){
	System.out.println(
	"aBlankString is blank!");
}
```

**Output:**

```
aEmptyString is empty!
aBlankString is blank!
```

## Stripping Whitespace

Like with checking if a String contains only whitepsace characters, removing leading and trailing whitespace characters is also a common requirement. Also a part of Java 11, three methods were added to String to address these needs; 

* `stripLeading()` : Returns a String with all leading whitespace characters removed.
* `stripTrailing()` : Returns a String with all trailing whitespace characters removed.
* `strip()` : Returns a String with all leading and trailing whitespace characters removed.

Like with `isBlank()` whitespace is based on `java.lang.Character.isWhitespace(int codePoint)`. 

```java
String aPaddedString = "   a padded string   ";
System.out.println(
  "stripLeading {" + aPaddedString.stripLeading() + "}");
System.out.println(
  "stripTrailing {" + aPaddedString.stripTrailing() + "}");
System.out.println(
  "strip {" + aPaddedString.strip() + "}");
```

**Output:**

```
stripLeading {a padded string   }
stripTrailing {   a padded string}
strip {a padded string}
```

## lines

`lines()`, also a part of the Java 11 release, breaks apart a String by carriage returns ((U+000A) "\n", (U+000D) "\r", (U+000D U+000A)  "\r\n"), and returns the result as a `Stream<String>`. This can be useful for when ingesting a data file as a String where each line in the String represents a new record.

```java	
String stringWithManyLines = """
		lorem 
		ipsum 
		dolor 
		sit 
		amet
		""";
	
Stream<String> lines 
	= stringWithManyLines.lines();
	
lines.forEach(
	System.out::print);//print as a single line
```

**Output:**

```
loremipsumdolorsitamet
```

## transform

`transform()`, added in Java 12, allows for a function to be to a String and returns the result of the function. (

```java
String aStringToTransform = "Transform me!";
String transformedString 
	= aStringToTransform.transform(s -> s.toUpperCase());
System.out.println(transformedString);
```

**Output:**

```
TRANSFORM ME!
```

## formatted

`formatted()`, added in Java 15, allows formatting to be applied to a String without first needing to create a `java.util.Formatter` instance.

```java
String aFormattedString = 
	"The current version of %s is %d";

System.out.println(
	aFormattedString.formatted("Java", 16));
```

**Output:**

```
The current version of Java is 16
```

Happy Coding!
