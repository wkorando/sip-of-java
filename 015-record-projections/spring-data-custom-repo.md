# Spring Data - Custom Repo
```java
public interface AdvocateRepo 
	extends CrudRepository<AdvocateEntity, Integer>,
	 CustomAdvocateRepo {
}
```
## Custom Repo Interface
```java
public interface CustomAdvocateRepo {
	Iterable<AdvocateNameRecord> findAllNameRecords();
}
```
## Custom Repo Implementation
```java
public class CustomAdvocateRepoImpl implements CustomAdvocateRepo {
	private JdbcTemplate jdbcTemplate;

	protected CustomAdvocateRepoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class AdvocateRecordDtoRowMapper 
	implements RowMapper<AdvocateNameRecord> {
		@Override
		public AdvocateNameRecord 
		mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new AdvocateNameRecord(
					rs.getString("f_name"), rs.getString("l_name"));
		}
	}

	@Override
	public Iterable<AdvocateNameRecord> findAllNameRecords() {
		return jdbcTemplate.query(
		"SELECT f_name, l_name FROM advocates", 
			new AdvocateRecordDtoRowMapper());

	}
}
```