# Moshi 
```java
Moshi moshi = new Moshi.Builder().build();
record LocalPoint(int x, int y) {}

JsonAdapter<TopLevelPoint> topLevelPointAdapter = moshi.adapter(TopLevelPoint.class);
JsonAdapter<InnerPoint> innerPointAdapter = moshi.adapter(InnerPoint.class);
//Local Records Not Supported: 
//JsonAdapter<LocalPoint> localPointAdapter = moshi.adapter(LocalPoint.class);

// Top Level Record
String json = topLevelPointAdapter.toJson(new TopLevelPoint(1, 2));
System.out.println(json);
System.out.println(topLevelPointAdapter.fromJson(json));

// Inner Record
json = innerPointAdapter.toJson(new InnerPoint(1, 2));
System.out.println(json);
System.out.println(innerPointAdapter.fromJson(json));

// Local Record
//json = localPointAdapter.toJson(new LocalPoint(1, 2));
//System.out.println(json);
//System.out.println(localPointAdapter.fromJson(json));
```

### Ouput:

```
{"x":1,"y":2}
TopLevelPoint[x=1, y=2]
{"x":1,"y":2}
InnerPoint[x=1, y=2]
```