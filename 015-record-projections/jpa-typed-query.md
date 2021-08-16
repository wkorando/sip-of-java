# JPA - Typed Query
```java
public List<AdvocateNameRecord> 
	findAdvocateNamesByRegion(String region) {

	TypedQuery<AdvocateNameRecord> query 
		= em.createQuery("""
			SELECT
			new com.bk.records.AdvocateNameRecord(
			a.fName, a.lName)
			FROM AdvocateEntity a
			WHERE region = :region
			""", AdvocateNameRecord.class);

	query.setParameter("region", region);
	return query.getResultList();
}
```