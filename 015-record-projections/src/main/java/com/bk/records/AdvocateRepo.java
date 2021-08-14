package com.bk.records;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdvocateRepo 
	extends CrudRepository<AdvocateEntity, Integer>, CustomAdvocateRepo {
	
	Iterable<AdvocateRecord> findByRegion(String region);

	@Query("""
	       SELECT 
	       new com.bk.records.AdvocateNameRecord(a.fName, a.lName)
	       FROM AdvocateEntity a
	       WHERE region = ?1
	       """)
	Iterable<AdvocateNameRecord> findNamesByRegion(String region);
}
