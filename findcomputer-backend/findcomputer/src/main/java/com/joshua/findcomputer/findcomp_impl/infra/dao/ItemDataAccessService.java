package com.joshua.findcomputer.findcomp_impl.infra.dao;

import com.joshua.findcomputer.findcomp_api.infra.dao.ItemDAO;
import com.joshua.findcomputer.findcomp_impl.helper.PostgresHelper;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
			,itemDataEntity.getCategory()
			,itemDataEntity.getOwner()
			,itemDataEntity.getName()
			,itemDataEntity.getDescription()
			,itemDataEntity.getPrice()
		);
	}

	@Override
	public List<ItemDataEntity> index(String owner, String category) {
		// TODO: if there's time updgrade this for list of owners and cats
		final String sql = PostgresHelper.selectOperation(new ItemDataEntity())
			+ " WHERE "+ItemDataEntity.ISACTIVE+" = true"
			+ (!owner.equals("") ? " AND "+ItemDataEntity.OWNER+" = \'"+owner+"\'" : "")
			+ (!category.equals("") ? " AND "+ItemDataEntity.CAT+" = \'"+category+"\'" : "");

		return jdbcTemplate.query(
			sql, ((resultSet, i) -> {
				return convertResultSetToDataEntity(resultSet);
			})
		);
	}

	@Override
	public Optional<ItemDataEntity> show(UUID id) {
		final String sql = PostgresHelper.selectOperation(new ItemDataEntity())
			+ " WHERE "+ItemDataEntity.ID +" = ?";
		try {
			ItemDataEntity itemDataEntity = jdbcTemplate.queryForObject(sql, new Object[]{id},
				((resultSet, i) -> {
					return convertResultSetToDataEntity(resultSet);
				}));
			return Optional.ofNullable(itemDataEntity);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public Integer update(ItemDataEntity itemDataEntity) {
		HashMap<String,Object> setter = new HashMap<>();
		setter.put(ItemDataEntity.ID, itemDataEntity.getId());
		setter.put(ItemDataEntity.NAME, itemDataEntity.getName());
		setter.put(ItemDataEntity.DES, itemDataEntity.getDescription());
		setter.put(ItemDataEntity.PRICE, itemDataEntity.getPrice());
		setter.put(ItemDataEntity.OWNER, itemDataEntity.getOwner());
		setter.put(ItemDataEntity.CAT, itemDataEntity.getCategory());
		setter.put(ItemDataEntity.ISACTIVE, true);
		final String sql = PostgresHelper.updateOperation(itemDataEntity,
			setter,ItemDataEntity.ID+" = \'" +itemDataEntity.getId()+"\'");
		return jdbcTemplate.update(sql);
	}

	@Override
	public Integer delete(UUID idItem) {
		ItemDataEntity itemDataEntity = show(idItem).orElse(null);
		if(itemDataEntity == null) return 0;
		HashMap<String,Object> setter = new HashMap<>();
		setter.put(ItemDataEntity.ISACTIVE, false);
		final String sql = PostgresHelper.updateOperation(itemDataEntity,
			setter,ItemDataEntity.ID+" = \'" +idItem+"\'");
		return jdbcTemplate.update(sql);
	}
}
