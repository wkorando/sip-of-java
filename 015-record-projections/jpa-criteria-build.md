# JPA - Criteria Builder
```java
public List<AdvocateRecord> findAllWithCriteriaBuilder() {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<AdvocateRecord> cq 
		= cb.createQuery(AdvocateRecord.class);

	Root<AdvocateEntity> root = cq.from(AdvocateEntity.class);
	
	cq.select(cb.construct(
			AdvocateRecord.class, 
			root.get("id"), 
			root.get("fName"), 
			root.get("lName"),
			root.get("region"), 
			root.get("twitterFollowers")));
	
	TypedQuery<AdvocateRecord> q = em.createQuery(cq);
	return q.getResultList();
}
```