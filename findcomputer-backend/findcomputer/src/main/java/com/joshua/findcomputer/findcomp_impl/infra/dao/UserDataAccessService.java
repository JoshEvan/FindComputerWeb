package com.joshua.findcomputer.findcomp_impl.infra.dao;

import com.joshua.findcomputer.findcomp_api.infra.dao.UserDAO;
import com.joshua.findcomputer.findcomp_impl.helper.PostgresHelper;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("pgUser")
public class UserDataAccessService implements UserDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public UserDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Integer register(UserDataEntity userDataEntity) {
		final String sql = PostgresHelper.insertOperation(userDataEntity);
		return jdbcTemplate.update(sql
			,userDataEntity.getUsername()
			,userDataEntity.getPassword()
		);
	}
}
