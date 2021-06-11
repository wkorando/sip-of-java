
public class StringEnhancementsI {

	public static void main(String[] args) {

		String aEmptyString = "";
		if(aEmptyString.isEmpty()){
			System.out.println("aEmptyString is empty!");
		}
		
		String aBlankString = "            ";
		if(aBlankString.isBlank()){
			System.out.println("aBlankString is blank!");
		}
	}

}
