public class InstanceofII {

	public static void main(String[] args) {
		Object actuallyAString = "I'm actually a string!";
		
		if(!(actuallyAString instanceof String nowImAString)) {
			nowImAString = "";
		} else {
			nowImAString = "";
		}
		System.out.println(nowImAString);
	}

}
