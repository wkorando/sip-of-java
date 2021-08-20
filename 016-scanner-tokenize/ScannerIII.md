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
	while (scanner.hasNext()) {
		if (scanner.hasNextInt()) {
			System.out.println(
			scanner.nextInt() * 2);
		} else {
			System.out.println(scanner.next());
		}
	}
}
```

## Output:

```
Billy Korando NA 5200
David Delabasse EMEA 17044
Denys Makogon EMEA 466
José Paumard EMEA 12262
Nicolai Parlog EMEA 24800
```
