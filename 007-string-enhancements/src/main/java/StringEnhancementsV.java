import java.util.Formatter;

public class StringEnhancementsV {

	public static void main(String[] args) {
		String aFormattedString = "The current version of %s is %d";

		System.out.println(aFormattedString.formatted("Java", 16));
	}

}
