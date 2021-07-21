import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileReaderI {
	public static void main(String[] args) {

		record Project(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city) {
		}
		List<Project> projectsList = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(new File("data.csv")))) {
			String line = br.readLine();
			
			while (line != null) {
				if (!line.startsWith("Reporting Period")) {
					String[] values = line.split(",");
					Project project = new Project(LocalDate.parse(values[0], DateTimeFormatter.ofPattern("M/dd/yy")),
							values[1], values[2], values[3]);
					if(project.city().equals("Babylon")) {
						projectsList.add(project);
						if(projectsList.size() == 2) {
							break;
						}
					}
				}
				line = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		projectsList.stream().forEach(p -> System.out.println(p.toString()));
	}
}