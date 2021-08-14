package com.bk.records;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class CustomAdvocateRepoImpl implements CustomAdvocateRepo {
	private JdbcTemplate jdbcTemplate;

	protected CustomAdvocateRepoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	class AdvocateRecordDtoRowMapper implements RowMapper<AdvocateNameRecord> {
		@Override
		public AdvocateNameRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new AdvocateNameRecord(
					rs.getString("f_name"), rs.getString("l_name"));
		}
	}

	@Override
	public Iterable<AdvocateNameRecord> findAllNameRecords() {
		return jdbcTemplate.query("SELECT f_name, l_name FROM advocates", new AdvocateRecordDtoRowMapper());

	}
}
