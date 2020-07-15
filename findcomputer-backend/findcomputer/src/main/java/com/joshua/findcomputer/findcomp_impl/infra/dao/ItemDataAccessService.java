package com.joshua.findcomputer.findcomp_impl.infra.dao;

import com.joshua.findcomputer.findcomp_api.infra.dao.ItemDAO;
import com.joshua.findcomputer.findcomp_impl.helper.PostgresHelper;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.joshua.findcomputer.findcomp_impl.infra.adapter.ItemAdapter.convertResultSetToDataEntity;

@Repository("pgItem")
public class ItemDataAccessService implements ItemDAO {
	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public ItemDataAccessService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Integer insert(ItemDataEntity itemDataEntity) {
		// ordered by DDL sql
		final String sql = PostgresHelper.insertOperation(itemDataEntity);
		return jdbcTemplate.update(sql
			,itemDataEntity.getId()
			,itemDataEntity.getCategoryId()
			,itemDataEntity.getOwner()
			,itemDataEntity.getName()
			,itemDataEntity.getDescription()
			,itemDataEntity.getPrice()
		);
	}

	@Override
	public List<ItemDataEntity> index() {
		final String sql = PostgresHelper.selectOperation(new ItemDataEntity())
			+ " WHERE "+ItemDataEntity.ISACTIVE+" = true";
		return jdbcTemplate.query(
			sql, ((resultSet, i) -> {
				ItemDataEntity dataEntity = convertResultSetToDataEntity(resultSet);
				return dataEntity;
			})
		);
	}

	@Override
	public Optional<ItemDataEntity> show(String id) {
		return Optional.empty();
	}

	@Override
	public Integer update(ItemDataEntity itemDataEntity) {
		return null;
	}

	@Override
	public Integer delete(String idItem) {
		return null;
	}
}
