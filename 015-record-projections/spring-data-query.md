# Spring Data - Query

```java
public interface AdvocateRepo 
	extends CrudRepository<AdvocateEntity, Integer> {
	@Query("""
	       SELECT 
	       new com.bk.records.AdvocateNameRecord(a.fName, a.lName)
	       FROM AdvocateEntity a
	       WHERE region = ?1
	       """)
	Iterable<AdvocateNameRecord> findNamesByRegion(String region);
}
```