package com.joshua.findcomputer.findcomp_impl.infra.dao;

import com.joshua.findcomputer.findcomp_api.infra.dao.UserDAO;
import com.joshua.findcomputer.findcomp_impl.helper.PostgresHelper;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

import static com.joshua.findcomputer.findcomp_impl.infra.adapter.UserAdapter.convertResultSetToDataEntity;
import static com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity.*;

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
			,userDataEntity.getProfileInfo()
		);
	}

	public List<UserDataEntity> index(){
		final String sql = PostgresHelper.selectOperation(new UserDataEntity())
			+ " WHERE "+ ISACTIVE+" = true";

		return jdbcTemplate.query(
			sql, ((resultSet, i) -> {
				UserDataEntity dataEntity = convertResultSetToDataEntity(resultSet);
				return dataEntity;
			})
		);
	}

	@Override
	public Integer update(UserDataEntity userDataEntity) {
		HashMap<String,Object> setter = new HashMap<>();
		setter.put(USERNAME, userDataEntity.getUsername());
		setter.put(PASSW, userDataEntity.getPassword());
		setter.put(PROFIL, userDataEntity.getProfileInfo());
		setter.put(ItemDataEntity.ISACTIVE, true);
		final String sql = PostgresHelper.updateOperation(userDataEntity,
			setter,USERNAME+" = \'" +userDataEntity.getUsername()+"\'");
		return jdbcTemplate.update(sql);
	}
}
