
public class StringEnhancementsII {

	public static void main(String[] args) {

		String aPaddedString = "   a padded string   ";
		System.out.println("stripLeading {" + aPaddedString.stripLeading() + "}");
		System.out.println("stripTrailing {" + aPaddedString.stripTrailing() + "}");
		System.out.println("strip {" + aPaddedString.strip() + "}");

	}

}
