import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileReaderI {
	public static void main(String[] args) {
		
		record Project(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city) {
		}
		List<Project> projectsList = new ArrayList<>();
		String filename = args[0];
		Month currentMonth = Month.JUNE;
		
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
			String line = br.readLine();
			
			while (line != null) {
				if (!line.startsWith("Reporting Period")) {
					String[] values = line.split(",");
					Project project = new Project(LocalDate.parse(values[0], DateTimeFormatter.ofPattern("M/dd/yy")),
							values[1], values[2], values[3]);
					if(project.reportingPeriod.getMonth().equals(currentMonth)) {
						projectsList.add(project);
					} else {
						break;
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(projectsList.size());
	}
}