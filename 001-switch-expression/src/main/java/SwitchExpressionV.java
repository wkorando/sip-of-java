public class SwitchExpressionV {
	
	enum DaysOfWeek {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY ;
	}
	
	public static void main(String[] args) {
		
		DaysOfWeek dayOfWeek = DaysOfWeek.SUNDAY;
		String result = switch (dayOfWeek) {
		case SUNDAY -> "Sunday";
		case MONDAY -> "Monday";
		case TUESDAY -> "Tuesday";
		case WEDNESDAY -> "Wednesday";
		case THURSDAY -> "Thursday";
		case FRIDAY -> "Friday";
		case SATURDAY -> "Saturday";
		};
		
		System.out.println(result);
	}
}