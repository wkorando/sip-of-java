import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class FileReaderIII {
	public static void main(String[] args) {

		record Project(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city) {
		}
		List<Project> projectsList = null;

		BiConsumer<String, Consumer<Project>> flatMapper = (line, consumer) -> {
			String[] values = line.split(",");
			Project project = new Project(LocalDate.parse(values[0], DateTimeFormatter.ofPattern("M/dd/yy")),
					values[1], values[2], values[3]);
			if (project.city().equals("Babylon")) {
				consumer.accept(project);
			}
		};
		
		try (Stream<String> lines = Files.lines(Path.of("data.csv"))) {
			projectsList = lines.filter(line -> !line.startsWith("Reporting Period")).mapMulti(flatMapper).limit(2).toList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		projectsList.stream().forEach(p -> System.out.println(p.toString()));
	}

}