```java
public class FileReaderI {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		Month currentMonth = Month.JUNE;
		SortedMap<String, ElectricProject> projectsByCity 
			= new TreeMap<>();
		String filename = args[0];

		try (BufferedReader br 
					= new BufferedReader(
						new FileReader(new File(filename)))) {
			String line = br.readLine();

			while (line != null) {
				if (!line.startsWith("Reporting Period")) {
					String[] values = line.split(",");
					if (LocalDate.parse(values[0], formatter).
						getMonth().equals(currentMonth)) {
						try {
							ElectricProject project = ElectricProject.map(values);
							if (!projectsByCity.containsKey(project.city())) {
								projectsByCity.put(project.city(), project);
							} else {
								ElectricProject existingProject = 
									projectsByCity.get(project.city());
								if (project.expectedKWhAnnualProduction()
										.compareTo(existingProject
											.expectedKWhAnnualProduction()) > 0) {
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