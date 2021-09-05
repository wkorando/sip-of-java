import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

public class MoshiMarshalling {
	record Point(int x, int y) {}

	public static void main(String[] args) {
		Moshi moshi = new Moshi.Builder().build();
		JsonAdapter<Point> pointAdapter = moshi.adapter(Point.class);
		
		System.out.println(pointAdapter.toJson(new Point(1, 2)));
	}
}
