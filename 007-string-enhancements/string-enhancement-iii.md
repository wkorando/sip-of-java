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

## OUTPUT

```
loremipsumdolorsitamet
```