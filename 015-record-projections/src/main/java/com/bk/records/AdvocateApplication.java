package com.bk.records;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AdvocateApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AdvocateApplication.class, args);

		AdvocateRepo springRepo = context.getBean(AdvocateRepo.class);
		AdvocateJPARepo jpaRepo = context.getBean(AdvocateJPARepo.class);
		
		springRepo.save(new AdvocateEntity(1, "Billy", "Korando", "NA", 2550));
		springRepo.save(new AdvocateEntity(2, "David", "Delabasse", "EMEA", 8500));
		springRepo.save(new AdvocateEntity(3, "Denys", "Makogon", "EMEA", 233));
		springRepo.save(new AdvocateEntity(4, "José", "Paumard", "EMEA", 6120));
		springRepo.save(new AdvocateEntity(5, "Nicolai", "Parlog", "EMEA", 12400));

		System.out.println("Print out normal Java Bean entities - JPA Repo");
		jpaRepo.findAllWithCriteriaBuilder().forEach(System.out::println);
		
		System.out.println("Print out normal Java Bean entities - JPA Repo");
		jpaRepo.findAdvocateNamesByRegionTypedQuery("NA").forEach(System.out::println);
		
		System.out.println("Print out normal Java Bean entities - JPA Repo");
		jpaRepo.findAdvocateNamesByIdNativeQuery(3).forEach(System.out::println);
		
		System.out.println("Print out normal Java Bean entities - Spring Repo");
		springRepo.findAll().forEach(System.out::println);

		System.out.println("Print out Record equivalent of AdvocateEntity - Spring Repo");
		springRepo.findByRegion("EMEA").forEach(System.out::println);

		System.out.println("Print out Record that only has name fields - Spring Repo");
		springRepo.findNamesByRegion("EMEA").forEach(System.out::println);

		System.out.println("Print out Record that only has name fields - Spring Repo");
		springRepo.findAllNameRecords().forEach(System.out::println);
	}

}
