```java
String advocates = """
		Billy,Korando,NA,2600
		David,Delabasse,EMEA,8522
		Denys,Makogon,EMEA,233
		José,Paumard,EMEA,6131
		Nicolai,Parlog,EMEA,12400
		""";

try (Scanner scanner = new Scanner(advocates)
.useDelimiter("[,\n]+")) {
	Stream<String> stream = scanner.tokens();

	stream.forEach(System.out::println);
}

try (Scanner scanner = new Scanner(advocates)
.useDelimiter(Pattern.compile("[,\n]+"))) {
	Stream<String> stream = scanner.tokens();

	stream.forEach(System.out::println);
}
```