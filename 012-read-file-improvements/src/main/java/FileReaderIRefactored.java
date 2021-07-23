import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.SortedMap;
import java.util.TreeMap;

public class FileReaderIRefactored {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		Month currentMonth = Month.JUNE;
		SortedMap<String, ElectricProject> projectsByCity = new TreeMap<>();
		String filename = args[0];

		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
			String line = br.readLine();

			while (line != null) {
				if (skipTitleLine(line)) {
					String[] values = line.split(",");
					if (isCurrentMonth(formatter, currentMonth, values)) {
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

	private static boolean skipTitleLine(String line) {
		return !line.startsWith("Reporting Period");
	}

	private static boolean isCurrentMonth(DateTimeFormatter formatter, Month currentMonth, String[] values) {
		return LocalDate.parse(values[0], formatter).getMonth().equals(currentMonth);
	}
}