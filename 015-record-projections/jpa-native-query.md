# JPA - Native Query
```java
public List<AdvocateNameRecord> 
	findAdvocateNamesByIdNativeQuery(int id) {
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