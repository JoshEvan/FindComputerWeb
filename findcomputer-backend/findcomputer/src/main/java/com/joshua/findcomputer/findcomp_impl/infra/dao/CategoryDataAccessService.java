package com.joshua.findcomputer.findcomp_impl.infra.dao;

import com.joshua.findcomputer.findcomp_api.infra.dao.CategoryDAO;
import com.joshua.findcomputer.findcomp_impl.helper.PostgresHelper;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.CategoryDataEntity;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.joshua.findcomputer.findcomp_impl.infra.flushout.FindcompDataEntity.ISACTIVE;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.CategoryAdapter.convertResultSetToDataEntity;

@Repository("pgCat")
public class CategoryDataAccessService implements CategoryDAO {
	private final JdbcTemplate jdbcTemplate;

	public CategoryDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List<CategoryDataEntity> index() {
		final String sql = PostgresHelper.selectOperation(new CategoryDataEntity())
			+ " WHERE "+ ISACTIVE+" = true";
		return jdbcTemplate.query(
			sql, ((resultSet, i) -> {
				return convertResultSetToDataEntity(resultSet);
			})
		);
	}
}
