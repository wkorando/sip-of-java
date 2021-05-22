import static java.util.Map.*;

import java.util.Map;

public class CollectionFactoryV {

	public static void main(String[] args) {
		Map<Integer, String> values = Map.ofEntries(entry( 1, "value 1"), //
													entry(2, "value 2"), //
													entry(3, "value 3"));
	}

}
