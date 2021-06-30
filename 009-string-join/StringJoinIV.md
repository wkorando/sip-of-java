```java
List<String> devAdvocates =  List.of("Billy", "David", "Denys", "José", "Nicolai");
		
String devAdvocatesList = devAdvocates.stream().collect(Collectors.joining(", ", "{ ", " }"));
		
System.out.println(devAdvocatesList);
```