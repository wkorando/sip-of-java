import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.TypeAdapter;

public class GSONMarshalling {
	record Point(int x, int y) implements InstanceCreator<Point>{
		public Point(int x, int y) {
			this.x = 0;
			this.y = 0;
			
		}

		@Override
		public GSONMarshalling.Point createInstance(Type type) {
			System.out.println(type);
			return null;
		}
		
	}
	record ZPoint(int z, Point point) {}
	record ListPoints(String name, List<Point> point) {}
	public static void main(String[] args) {
		Gson gson = new Gson();
		String json = gson.toJson(new Point(1, 2));
		System.out.println(gson.fromJson(json, Point.class).toString());
		System.out.println(gson.toJson(new ListPoints("stuff", List.of(new Point(1, 2),new Point(1, 2),new Point(1, 2),new Point(1, 2)))));
	}
}
