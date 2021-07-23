# Streams and File Reading Improvements

Organizations often need to process formatted data files from clients, partners, or through internal processes. 

Historically in Java these files were processed using imperative design constructs, if statements, loops, etc.. With Java 8 stream were introduced that could greatly simplify the design of this processing. Streams has also seen several updates since Java 8, that further improves this process.

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

## Functional Porcessing with Streams

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

## Other Streams Improvements

```java
lines.skip(titleLine) //
		.map(s -> s.split(",")).takeWhile(filterToCurrentMonth)//
		.mapMulti(mapElectricProject) //
		.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
				Collectors.maxBy(compareExpectedKwhProduction) )) //
		.values().stream().map(Optional::get).forEach(printResults);
```

```java
lines.skip(titleLine) //
		.map(s -> s.split(",")).dropWhile(filterToCurrentMonth)//
		.mapMulti(mapElectricProject) //
		.collect(Collectors.groupingBy(ElectricProject::city, TreeMap::new,
				Collectors.maxBy(compareExpectedKwhProduction) )) //
		.values().stream().map(Optional::get).forEach(printResults);
```


## Further Learning

Additional reading?

Happy Coding!