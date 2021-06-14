# Convenience Factory Methods for Collections

Convenience Factory Methods for Collections, added in JDK 9 ([JEP 269](https://openjdk.java.net/jeps/269)), were a set of methods designed to make the instantiation of collections easier. 

## Verbosity When Creating Collections 

Creating even simple collections, like the below `ArrayList` example required a fair amount of typing to accomplish:

```java
List<String> values = 
	new ArrayList<>();
values.add("value 1");
values.add("value 2");
values.add("value 3");
```
 
## Collections of

To lower the formality around creating simple collections `List`, `Set`, and `Map` each had a default method `of` defined for creating an unmodifiable instance of the respective collection type like in the examples below for `List` and `Set`:

**List:**

```java
List<String> values = List.of(
	"value 1", 
	"value 2", 
	"value 3");
```

**Set:**
```java
Set<String> values = Set.of(
	"value 1", 
	"value 2", 
	"value 3");
```

### Map.of

When creating a `Map` instance using `Map.of` the types of arguments passed into `of` must match the key and value of the the map like in the below example of `Integer` and `String`:

```java
Map<Integer, String> values = Map.of(
	1, "value 1", 
	2, "value 2", 
	3, "value 3");
```

## Map ofEntries

If more clarity is needed when creating a simple `Map` instance, you could also use `Map.ofEntries` with `java.util.Map.entry`, like in the below example: 

```java
import static java.util.Map.entry;

...
Map<Integer, String> values = Map.ofEntries(
	entry(1, "value 1"), 
	entry(2, "value 2"), 
	entry(3, "value 3"));
...
```

Happy coding! 