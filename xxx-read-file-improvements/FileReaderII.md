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