```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
StringJoiner joiner = new StringJoiner(", ", "{ ", " }");
for(String devAdvocate : devAdvocates) {
	joiner.add(devAdvocate);
}
		
System.out.println(joiner.toString());
```
