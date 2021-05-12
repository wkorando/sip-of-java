public class SwitchExpressionI {
	public static void main(String[] args) {
		String input = args[0];
		if (input.equals("1")) {
			System.out.println("Monday");
		} else if (input.equals("2")) {
			System.out.println("Tuesday");
		} else if (input.equals("3")) {
			System.out.println("Wednesday");
		} else if (input.equals("4")) {
			System.out.println("Thursday");
		} else if (input.equals("5")) {
			System.out.println("Friday");
		} else if (input.equals("6")) {
			System.out.println("Saturday");
		} else if (input.equals("7")) {
			System.out.println("Sunday");
		} else {
			System.out.println("Invalid selection, valid choices 1-7");
		}
	}
}