import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class GsonExample {
	private record InnerPoint(int x, int y) {}

	static class TopLevelPointDeserializer 
	implements JsonDeserializer<TopLevelPoint> {
		@Override
		public TopLevelPoint deserialize(
				JsonElement json, 
				Type typeOfT, 
				JsonDeserializationContext context)
				throws JsonParseException {
			final JsonObject jObject = json.getAsJsonObject();
			final int x = jObject.get("x").getAsInt();
			final int y = jObject.get("y").getAsInt();
			return new TopLevelPoint(x, y);
		}
	}
	
	static class InnerPointDeserializer 
	implements JsonDeserializer<InnerPoint> {
		@Override
		public InnerPoint deserialize(
				JsonElement json, 
				Type typeOfT, 
				JsonDeserializationContext context)
				throws JsonParseException {
			final JsonObject jObject = json.getAsJsonObject();
			final int x = jObject.get("x").getAsInt();
			final int y = jObject.get("y").getAsInt();
			return new InnerPoint(x, y);
		}
	}

	public static void main(String[] args) {
		record LocalPoint(int x, int y) {}
		
		Gson gson = new GsonBuilder().
			registerTypeAdapter(TopLevelPoint.class, new TopLevelPointDeserializer()).
			registerTypeAdapter(InnerPoint.class, new InnerPointDeserializer()).
			create();
		
		//Top Level Record
		String json = gson.toJson(new TopLevelPoint(1, 2));
		System.out.println(json);
		System.out.println(gson.fromJson(json, TopLevelPoint.class));
		
		//Inner Record
		json = gson.toJson(new InnerPoint(1, 2));
		System.out.println(json);
		System.out.println(gson.fromJson(json, InnerPoint.class));
		
		//Local Record
		json = gson.toJson(new LocalPoint(1, 2));
		System.out.println(json);
		System.out.println(gson.fromJson(json, LocalPoint.class));
	}
}