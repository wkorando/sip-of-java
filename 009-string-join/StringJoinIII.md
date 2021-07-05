```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
		
StringJoiner joiner = new StringJoiner(", ", "{ ", " }");


devAdvocates.forEach(joiner::add);



		
System.out.println(joiner.toString());
```

## OUTPUT

```
{ Billy, David, Denys, José, Nicolai }
```