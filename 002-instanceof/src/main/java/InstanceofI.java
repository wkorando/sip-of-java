public class InstanceofI {

	public static void main(String[] args) {
		Object actuallyAString = "I'm actually a string!";
		
		if(actuallyAString instanceof String) {
			String nowImAString = (String) actuallyAString;
			
			System.out.println(nowImAString);
		}
	}

}
