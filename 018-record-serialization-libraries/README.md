# Record Serialization Libraries

Code - Video - Script

Records introduced with Java 16, JEP 395, were designed to be transparent data carriers. This  would make them an ideal for a message to be serialized and deserialized between services. In this article we will look at how three popular libraries are supporting Records. 

## Jackson

Jackson was the first java serialization library to offer support for Records with version 2.12.0. It also provides the best and most flexible support. Let's look at the below example:

```java
public class JacksonExample {
	private record InnerPoint(int x, int y) {}
	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		record LocalPoint(int x, int y) {}
		
		//Top Level Record
		String json = mapper.writeValueAsString(new TopLevelPoint(1, 2));
		System.out.println(json);
		System.out.println(mapper.readValue(json, TopLevelPoint.class));
		
		//Inner Record
		json = mapper.writeValueAsString(new InnerPoint(1, 2));
		System.out.println(json);
		System.out.println(mapper.readValue(json, InnerPoint.class));
		
		//Local Record
		json = mapper.writeValueAsString(new LocalPoint(1, 2));
		System.out.println(json);
		System.out.println(mapper.readValue(json, LocalPoint.class));
	}
}
```
### Output

```
{"x":1,"y":2}
TopLevelPoint[x=1, y=2]
{"x":1,"y":2}
InnerPoint[x=1, y=2]
{"x":1,"y":2}
LocalPoint[x=1, y=2]
```

Jackson is able to support Records defined at a [top level class](NEED LINK), an inner class `InnerPoint` and as a local class. Jackson is also able to support serialization and deserialization of Records regardless of the visibility of the Record. 

For flexibility and ease of use Jackson would be the best current option for a serialization library for Records. 

## Gson

As of version 2.8.8 of Gson, only limited support of Records is offered, and as of the writing of this article, official Records support is not on the roadmap.  

```java
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
```

### Output

```
{"x":1,"y":2}
TopLevelPoint[x=1, y=2]
{"x":1,"y":2}
InnerPoint[x=1, y=2]
null
null
```

Gson is able to serialize Records defined as a top level class and as an inner class out of the box. Gson is also not impacted by the visibility of a Record class. Gson however does not support serializing or deserializing a local Record.

To deserialize a Record a `JsonDeserializer` needs to be defined and provided when creating a new `Gson` instance. Examples of a more generic Record deserializer can be seen in the [issue discussion for adding Record support to Gson](https://github.com/google/gson/issues/1794).

 
## Moshi

Moshi, [a proposed successor library to Gson](https://www.reddit.com/r/androiddev/comments/684flw/why_use_moshi_over_gson/dgx3gpm/?context=3), also currently only offers limited Record support as of version 1.12. However official Record support should be coming with 1.13\*. Let's look at how Moshi will provide Record support\**:

```java
public class MoshiExample {

	public record InnerPoint(int x, int y) {
	}

	public static void main(String[] args) throws IOException {
		Moshi moshi = new Moshi.Builder().build();
		record LocalPoint(int x, int y) {}
		
		JsonAdapter<TopLevelPoint> topLevelPointAdapter = moshi.adapter(TopLevelPoint.class);
		JsonAdapter<InnerPoint> innerPointAdapter = moshi.adapter(InnerPoint.class);
//		Local Records Not Supported:
//		JsonAdapter<LocalPoint> localPointAdapter = moshi.adapter(LocalPoint.class);

		// Top Level Record
		String json = topLevelPointAdapter.toJson(new TopLevelPoint(1, 2));
		System.out.println(json);
		System.out.println(topLevelPointAdapter.fromJson(json));

		// Inner Record
		json = innerPointAdapter.toJson(new InnerPoint(1, 2));
		System.out.println(json);
		System.out.println(innerPointAdapter.fromJson(json));

		// Local Record
//		json = localPointAdapter.toJson(new LocalPoint(1, 2));
//		System.out.println(json);
//		System.out.println(localPointAdapter.fromJson(json));
	}
}
```

### Ouput:

```
{"x":1,"y":2}
TopLevelPoint[x=1, y=2]
{"x":1,"y":2}
InnerPoint[x=1, y=2]
```

Moshi will only support serialization and deserialization of `public` Records. The Record can be defined as either a top-level of inner class. Moshi does not support Records with `protected`, `package private`, or `private` visibility throwing an `IllegalAccessException`.

\* The currently set version in the master branch Moshi

\** Above code example is using a locally built version of Moshi

## Further Reading

[JEP 395: Records](https://openjdk.java.net/jeps/395)

Jackson: [Support for record types in JDK 14](https://github.com/FasterXML/jackson-future-ideas/issues/46)

Gson: [Java 14/15 records can not set final field](https://github.com/google/gson/issues/1794)

Moshi: [Support Java record classes when they are stable](https://github.com/square/moshi/issues/1278)

Happy Coding!