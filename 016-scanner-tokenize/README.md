# Scanner Tokenize

[Code](https://github.com/wkorando/sip-of-java/tree/main/016-scanner-tokenize) - [Video](https://youtu.be/wyL6o2mYKlM) - [Script](https://github.com/wkorando/sip-of-java/blob/main/016-scanner-tokenize/script.srt)


The Scanner API, initially introduced in JDK 5, and having received updates in subsequent JDK releases provides an easy and accessible way to parse and tokenize an input source. 


## Scanner Input Sources

Scanner is able to parse many different input sources including:

```
* String
* File
* InputStream
* Readable
* ReadableByteChannel
* Path
```

## Parsing and Processing input as Stream

A regex delimiter can be provided to Scanner as a `String` or as a `Pattern`.

The parsed data can be processed as a `Stream<String>` with `tokens()` like in the example below:

```java
String advocates = """
		Billy,Korando,NA,2600
		David,Delabassée,EMEA,8522
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

## hasNext() and next()

The data can also be processed in a loop using `hasNext()` and `next()`:

```java
String advocates = """
		Billy,Korando,NA,2600
		David,Delabassée,EMEA,8522
		Denys,Makogon,EMEA,233
		José,Paumard,EMEA,6131
		Nicolai,Parlog,EMEA,12400
		""";

try (Scanner scanner = new Scanner(advocates)
.useDelimiter("[,\n]+")) {
	while (scanner.hasNext()) {
		System.out.println(scanner.next());
	}
}
```

## Type Detection and Conversion

Scanner has several different versions of `hasNext()` and `next()` implemented which can detect and convert the following types:

```
* BigDecimal
* BigInterger
* Boolean
* Byte
* Double
* Float
* Int
* Long
* Short
```

So in this example the final value in the line is converted to an Int type and can by doubled:

```java
String advocates = """
		Billy,Korando,NA,2600
		David,Delabassée,EMEA,8522
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

### Output:

```
Billy Korando NA 5200
David Delabassée EMEA 17044
Denys Makogon EMEA 466
José Paumard EMEA 12262
Nicolai Parlog EMEA 24800
```

## Localization

Scanner also allows for easy localization of data by passing in a `Locale` to `useLocale()`. In the below example the final value in the line is treated as a String with the `Locale.US`, as "." is only used as a decimal point in the US, but treated as a Integer with the `Locale.Germany` because "." is used to denote thousands/between every three integers in a number:

```java
String advocates = """
		Billy,Korando,NA,2.600
		David,Delabassée,EMEA,8.522
		Denys,Makogon,EMEA,233
		José,Paumard,EMEA,6.131
		Nicolai,Parlog,EMEA,12.400
		""";
System.out.println("US Locale\n");
try (Scanner scanner = new Scanner(advocates)
.useDelimiter("[,\n]+").useLocale(Locale.US)) {
	int i = 0;
	while (scanner.hasNext()) {
		i++;
		if (scanner.hasNextInt()) {
		// Will always be false because "."
		// is only used as a decimal in US
			System.out.print(scanner.nextInt() * 2);
		} else {
			System.out.print(scanner.next() + " ");
		}
		if (i % 4 == 0) {
			System.out.print("\n");
		}
	}
}

System.out.println("\nGermany Locale\n");
try (Scanner scanner = new Scanner(advocates)
.useDelimiter("[,\n]+").useLocale(Locale.GERMANY)) {
	int i = 0;
	while (scanner.hasNext()) {
		i++;
		if (scanner.hasNextInt()) {
		// Will return true for last item,
		// as "." is used like "," in Germany
			System.out.print(scanner.nextInt() * 2);
		} else {
			System.out.print(scanner.next() + " ");
		}

		if (i % 4 == 0) {
			System.out.print("\n");
		}
	}
}
```

### Output

```
US Locale

Billy Korando NA 2.600 
David Delabassée EMEA 8.522 
Denys Makogon EMEA 466
José Paumard EMEA 6.131 
Nicolai Parlog EMEA 12.400 

Germany Locale

Billy Korando NA 5200
David Delabassée EMEA 17044
Denys Makogon EMEA 466
José Paumard EMEA 12262
Nicolai Parlog EMEA 24800

```

## Further Reading

* Scanner is a Weird but Useful Beast, by Sutart Marks: [https://stuartmarks.wordpress.com/2020/04/14/scanner-is-a-weird-but-useful-beast/](https://stuartmarks.wordpress.com/2020/04/14/scanner-is-a-weird-but-useful-beast/)
* Scanner JDK 16 Javadoc - [https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Scanner.html](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/Scanner.html)

Happy Coding!