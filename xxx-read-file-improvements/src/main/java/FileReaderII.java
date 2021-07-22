import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FileReaderII {
	public static void main(String[] args) {

		record Project(LocalDate reportingPeriod, String projectNumber, String legacyProjectNumber, String city) {
		}
		List<Project> projectsList = null;
		String filename = args[0];
		Month currentMonth = Month.JUNE;

		Function<String, Stream<Project>> flatMapper = line -> {
			String[] values = line.split(",");
			Project project = new Project(LocalDate.parse(values[0], DateTimeFormatter.ofPattern("M/dd/yy")), values[1],
					values[2], values[3]);
			return Stream.of(project);
		};

		Predicate<String> titleLinePredicate = line -> {
			return !line.startsWith("Reporting Period");
		};

		Predicate<Project> thisMonth = project -> {
			return project.reportingPeriod.getMonth().equals(currentMonth);
		};

		try (Stream<String> lines = Files.lines(Path.of(filename))) {
			projectsList = lines.filter(titleLinePredicate).flatMap(flatMapper).takeWhile(thisMonth).toList();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(projectsList.size());
	}

}