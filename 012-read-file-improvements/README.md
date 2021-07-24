# Streams and File Reading Improvements

Organizations often need to process formatted data files from clients, partners, or from data generated through internal processes. 

Historically in Java these files were processed using imperative design constructs, if statements, loops, etc., which when performing complex processing operations, could become difficult to understand. With Java 8 [streams](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Stream.html) were introduced that could greatly simplify the design of this processing. Streams has also seen several updates since Java 8, that further improves this process.

## Data Model

For this example, a CSV file for solar projects in the state of New York is being processed, which can be freely downloaded [here.](https://catalog.data.gov/dataset/solar-electric-programs-reported-by-nyserda-beginning-2000)

The lines here are being modeled with the Record `ElectricProject`:

```java
public record ElectricProject(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city,
		String county, String state, String zipCode, String sector, String programType, String solicitation,
		String electricUtility, String purchaseType, LocalDate dateApplicationReceived, LocalDate dateCompleted,
		String projectStatus, String contractor, String primaryInverterManufacturer, String primaryInverterModelNumber,
		BigDecimal totalInverterQuantity, String primaryPVModuleManufacturer, String pvModuleModelNumber,
		BigDecimal totalPVModuleQuantity, BigDecimal projectCost, BigDecimal incentive, BigDecimal totalNameplatekWDC,
		BigDecimal expectedKWhAnnualProduction, String remoteNetMetering, String affordableSolar,
		String communityDistributed, String generationGreenJobsGreenNewYorkParticipant, String georeference) {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

	boolean reportingPeriodMatches(Month month) {
		return this.reportingPeriod.getMonth().equals(month);
	}

	static BigDecimal handleNumeric(String value) {
		try {
			return new BigDecimal(value);
		} catch (Exception e) {
			return BigDecimal.valueOf(0);
		}
	}

	static LocalDate handleDate(String value) {
		if (value.isBlank()) {
			return null;
		} else {
			return LocalDate.parse(value, formatter);
		}
	}

	static ElectricProject map(String[] values) {
		return new ElectricProject(handleDate(values[0]), values[1], values[2], values[3], values[4], values[5],
				values[6], values[7], values[8], values[9], values[10], values[11], handleDate(values[12]),
				handleDate(values[13]), values[14], values[15], values[16], values[17], handleNumeric(values[18]),
				values[19], values[20], handleNumeric(values[21]), handleNumeric(values[22]), handleNumeric(values[23]),
				handleNumeric(values[24]), handleNumeric(values[25]), values[26], values[27], values[28], values[29],
				values[30]);
	}
}
```

## Imperative Data Processing

In this imagined example, the goal of processing the retrieved data file is to find the highest producing solar project in every city. 

Accomplishing this relatively simple task imperatively might look like below, which requires looping through the data twice, and several nested if/else statements. Indeed in this example, it gets up to five layers deep! 

Which this example could be further refactored for [readability](https://github.com/wkorando/sip-of-java/blob/main/012-read-file-improvements/FileReaderIRefactored.md). As new requirements are added, imperative processes can become extremely difficult to follow, and subsequently modify and update.   

```java
public class FileReaderI {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		Month currentMonth = Month.JUNE;
		SortedMap<String, ElectricProject> projectsByCity = new TreeMap<>();
		String filename = args[0];

		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
			String line = br.readLine();

			while (line != null) {
				if (!line.startsWith("Reporting Period")) {
					String[] values = line.split(",");
					if (LocalDate.parse(values[0], formatter).getMonth().equals(currentMonth)) {
						try {
							ElectricProject project = ElectricProject.map(values);
							if (!projectsByCity.containsKey(project.city())) {
								projectsByCity.put(project.city(), project);
							} else {
								ElectricProject existingProject = projectsByCity.get(project.city());
								if (project.expectedKWhAnnualProduction()
										.compareTo(existingProject.expectedKWhAnnualProduction()) > 0) {
									projectsByCity.put(project.city(), project);
								}
							}
						} catch (Exception e) {
							// Do some cool stuff to handle errors
						}
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (ElectricProject project : projectsByCity.values()) {
			System.out.println("""
					City: %s
					Project Number: %s
					Expected Output: %s
					""".formatted(project.city(), project.projectNumber(),
					project.expectedKWhAnnualProduction().toString()));
		}
	}
}
```

## Functional Processing with Streams

Accomplishing the same task functionally using Streams, looks like below. While the line count is similar to the imperative implementation above, streams does offer several distinct advantages, which will be covered below. 

```java
public class FileReaderII {public class FileReaderII {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		Month currentMonth = Month.JUNE;
		String filename = args[0];
		long titleLine = 1L;

		BiConsumer<String[], Consumer<ElectricProject>> mapElectricProject = (values, consumer) -> {
			try {
				consumer.accept(ElectricProject.map(values));
			} catch (Exception e) {
				// Do some cool stuff to handle errors
			}
		};

		Consumer<ElectricProject> printResults = project -> {
			System.out.println("""
					City: %s
					Project Number: %s
					Expected Output: %s
					""".formatted(project.city(), project.projectNumber(),
					project.expectedKWhAnnualProduction().toString()));
		};

		Comparator<ElectricProject> compareExpectedKwhProduction = Comparator
				.comparing(ElectricProject::expectedKWhAnnualProduction);

		Predicate<String[]> filterToCurrentMonth = s -> LocalDate.parse(s[0], formatter).getMonth()
				.equals(currentMonth);

		try (Stream<String> lines = Files.lines(Path.of(filename))) {
			lines.skip(titleLine) //
					.map(s -> s.split(",")).filter(filterToCurrentMonth)//
					.mapMulti(mapElectricProject) //
					.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
							Collectors.maxBy(compareExpectedKwhProduction) )) //
					.values().stream().map(Optional::get).forEach(printResults);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

### Skipping elements

In many formatted data files, the first line(s) are often title or metadata lines and are part of the rows to be processed. In imperative design skipping these lines would be done through an if check, or by adding an counter variable, and skipping a predetermined number of lines. Streams make this much easier with `skip(long)`, like used in the above example with: `.skip(titleLine)`.

### mapMulti

Added in Java 16, `mapMulti()` ([JavaDoc](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Stream.html#mapMulti(java.util.function.BiConsumer))) allows the replacing of elements in a stream with 0 to n elements. In the above example, it is being used to more gracefully deal with the common issue of a read error. It can also be helpful in cases where data is across multiple lines, or the results need to be split up for some reason. 

### Grouping and Ordering

When processing data, often there is a need to group or order the data. Like in this example of finding the highest kWh generating solar project for each city. Many pre-defined `Collectors` are available, like the above shown `groupBy` and `maxBy`. Many [other options are available](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Collectors.html), that cover most use cases. And defining your own [Collector](https://docs.oracle.com/en/java/javase/16/docs/api/java.base/java/util/stream/Collector.html) is of course an option. 

## Other Streams Improvements

Since being added Streams have been continuously improved upon with each release, easing usage, and better addressing use cases. 


### Ordered and Infinite Data Sets

With Java 9, streams better handled data from ordered data sets and/or infinite (or extremely large) data sets with `takeWhile()` and `dropWhile()`
 
#### takeWhile() example

```java
lines.skip(titleLine) //
		.map(s -> s.split(",")).takeWhile(filterToCurrentMonth)//
		.mapMulti(mapElectricProject) //
		.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
				Collectors.maxBy(compareExpectedKwhProduction) )) //
		.values().stream().map(Optional::get).forEach(printResults);
```
#### dropWhile() example

```java
lines.skip(titleLine) //
		.map(s -> s.split(",")).dropWhile(filterToCurrentMonth)//
		.mapMulti(mapElectricProject) //
		.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
				Collectors.maxBy(compareExpectedKwhProduction) )) //
		.values().stream().map(Optional::get).forEach(printResults);
```

### Collecting into a List

Often after processing data through a stream, collecting the results into a `List` is needed. Previously with was done with `collect(Collectors.toList())`, but can now be more simply accomplished with `toList()` like below:

```java
List<ElectricProject> projects = lines.skip(titleLine) //
		.map(s -> s.split(",")).dropWhile(filterToCurrentMonth)//
		.mapMulti(mapElectricProject) //
		.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
				Collectors.maxBy(compareExpectedKwhProduction) )) //
		.values().stream().map(Optional::get).toList();
```

## Further Learning

Additional reading?

Happy Coding!