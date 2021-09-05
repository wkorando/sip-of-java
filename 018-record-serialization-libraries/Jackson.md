# Jackson
```java
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