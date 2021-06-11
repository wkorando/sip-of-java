public class InstanceofVII {

	public static void main(String[] args) {
		Object actuallyAString = "I'm actually a string!";

		if (!(actuallyAString instanceof String nowImAString)) {

		} else {
			System.out.println(nowImAString);
		}

		if (!(actuallyAString instanceof String nowImAString)) {
			throw new IllegalArgumentException("Must be a string!");
		}

		System.out.println(nowImAString);
	}
}
