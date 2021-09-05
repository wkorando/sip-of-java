# Gson - Deserialize
```java
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
```