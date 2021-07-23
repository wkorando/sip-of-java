import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReaderII {
	public static void main(String[] args) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		Month currentMonth = Month.JUNE;
		String filename = args[0];

		BiConsumer<String, Consumer<ElectricProject>> retrieveResultsForCurrentMonth = (line, consumer) -> {
			String[] values = line.split(",");
			if (LocalDate.parse(values[0], formatter).getMonth().equals(currentMonth)) {
				try {
					consumer.accept(ElectricProject.map(values));
				} catch (Exception e) {
					// Do some cool stuff to handle errors
				}
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

		Predicate<String> isTitleLine = line -> line.startsWith("Reporting Period");

		BinaryOperator<ElectricProject> findBestProducingProject = (newProject, currentProject) -> {
			if (newProject.expectedKWhAnnualProduction().compareTo(currentProject.expectedKWhAnnualProduction()) > 0) {
				return newProject;
			} else {
				return currentProject;
			}
		};

		try (Stream<String> lines = Files.lines(Path.of(filename))) {
			lines.filter(isTitleLine.negate()).mapMulti(retrieveResultsForCurrentMonth) //
					.collect(Collectors.toMap(ElectricProject::city, p -> p, findBestProducingProject, TreeMap::new))
					.values().stream().forEach(printResults);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}