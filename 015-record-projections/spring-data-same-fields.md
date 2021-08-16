# Spring Data - Automatic Mapping

```java
public interface AdvocateRepo 
	extends CrudRepository<AdvocateEntity, Integer> {
	Iterable<AdvocateRecord> 
		findByRegion(String region);
}
```

## Record:
```java
public record AdvocateRecord(
int id, 
String fName, 
String lName, 
String region, 
int twitterFollowers) {}
```

## Tracked Entity:
```
public class AdvocateEntity {
	@Id
	private int id;
	private String fName;
	private String lName;
	private String region;
	private int twitterFollowers;
...
```
