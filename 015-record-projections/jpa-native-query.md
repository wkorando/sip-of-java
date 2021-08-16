# JPA - Native Query

```java
public List<AdvocateNameRecord> 
	findAdvocateNamesById(int id) {
	Query query = em.createNativeQuery("""
			SELECT
			f_name, l_name
			FROM advocates
			WHERE id = :id
			""", 
			"AdvocateNameRecordMapping");
	query.setParameter("id", id);
	return query.getResultList();
}
```

## Mapping Definition:
```java
@Entity
@Table(name = "advocates")
@SqlResultSetMapping(
	name = "AdvocateNameRecordMapping",
	classes = @ConstructorResult(
		targetClass = AdvocateNameRecord.class,
			columns = { 
				@ColumnResult(name = "f_name"), 
				@ColumnResult(name = "l_name")}))
public class AdvocateEntity {
...
```
