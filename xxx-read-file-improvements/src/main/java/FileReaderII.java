import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class FileReaderII {
	public static void main(String[] args) {

		record Project(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city) {
		}
		List<Project> projectsList = null;

		Function<String, Stream<Project>> flatMapper = line -> {
			String[] values = line.split(",");
			Project project = new Project(LocalDate.parse(values[0], DateTimeFormatter.ofPattern("M/dd/yy")),
					values[1], values[2], values[3]);
			if (project.city().equals("Babylon")) {
				return Stream.of(project);
			} else {
				return Stream.of();
			}
		};
		
		try (Stream<String> lines = Files.lines(Path.of("data.csv"))) {
			projectsList = lines.filter(line -> !line.startsWith("Reporting Period")).flatMap(flatMapper).limit(2).toList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		projectsList.stream().forEach(p -> System.out.println(p.toString()));
	}

}