public class InstanceofVI {
	
	public static void main(String[] args) {
		Object actuallyAString = "I'm actually a string!";
		String aString = null;
		
		if(actuallyAString instanceof String nowImAString) {
			aString  = nowImAString;
		}
		
		System.out.println(aString);
	}
}
