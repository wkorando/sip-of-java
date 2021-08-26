import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.stream.Stream;

public class StreamPredicateIterate {

	public static void main(String[] args) {
		System.out.println("Remaining days of the week:");
		Stream.iterate(
				LocalDate.now().getDayOfWeek().getValue(), 
				d -> d <= DayOfWeek.SUNDAY.getValue(), 
				d -> ++d)
				.forEach(d -> System.out.println(DayOfWeek.of(d)));
	}
}