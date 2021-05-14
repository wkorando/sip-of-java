public class InstanceofIII {

	public static void main(String[] args) {
		Object actuallyAString = "I'm actually a string!";
		
		if(actuallyAString instanceof String nowImAString) {
			System.out.println(nowImAString);
		}
		
		System.out.println(nowImAString);
		
		boolean isAString = (actuallyAString instanceof String nowImAString);

		System.out.println(nowImAString);
	}

}
