import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileReaderIII {
	public static void main(String[] args) {

		record Project(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city) {
		}
		List<Project> projectsList = null;
		String filename = args[0];
		Month currentMonth = Month.JUNE;

		BiConsumer<String, Consumer<Project>> flatMapper = (line, consumer) -> {
			String[] values = line.split(",");
			Project project = new Project(LocalDate.parse(values[0], DateTimeFormatter.ofPattern("M/dd/yy")), values[1],
					values[2], values[3]);
			if (project.reportingPeriod.getMonth().equals(currentMonth)) {
				consumer.accept(project);
			}
		};
		
		Predicate<String> titleLinePredicate = line -> {
			return !line.startsWith("Reporting Period");
		};

		try (Stream<String> lines = Files.lines(Path.of(filename))) {
			projectsList = lines.filter(titleLinePredicate).mapMulti(flatMapper)
					.toList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(projectsList.size());
	}

}