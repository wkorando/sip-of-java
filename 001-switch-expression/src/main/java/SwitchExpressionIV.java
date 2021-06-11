public class SwitchExpressionIV {
	public static void main(String[] args) {
		
		String result = switch (args[0]) {
		case "1" -> "Sunday";
		case "2" -> "Monday";
		case "3" -> "Tuesday";
		case "4" -> "Wednesday";
		case "5" -> "Thursday";
		case "6" -> "Friday";
		case "7" -> "Saturday";
 		default -> {
			System.out.println("Invalid selection, valid choices 1-7");
			yield "Invalid Choice";
		}
		};
		
		System.out.println(result);
	}
}