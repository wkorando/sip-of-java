# Gson
```java
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