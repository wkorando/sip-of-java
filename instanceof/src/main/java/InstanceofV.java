public class InstanceofV {

	public static void main(String[] args) {
		Object actuallyAString = "I'm actually a string!";
		
		if(actuallyAString instanceof String nowImAString && nowImAString.endsWith("!")) {
			System.out.println(nowImAString);
		}
	}

}
