package com.bk.records;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Service;

@Service
public class AdvocateJPARepo {

	private EntityManager em;

	public AdvocateJPARepo(EntityManager em) {
		this.em = em;
	}

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

	public List<AdvocateNameRecord> findAdvocateNamesByRegionTypedQuery(String region) {
		TypedQuery<AdvocateNameRecord> query = em.createQuery("""
				SELECT
				new com.bk.records.AdvocateNameRecord(a.fName, a.lName)
				FROM AdvocateEntity a
				WHERE region = :region
				""", AdvocateNameRecord.class);

		query.setParameter("region", region);

		return query.getResultList();
	}

	public List<AdvocateNameRecord> findAdvocateNamesByIdNativeQuery(int id) {
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
}