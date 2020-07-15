package com.joshua.findcomputer.findcomp_api.infra.dao;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;

import java.util.List;
import java.util.Optional;

public interface ItemDAO {
	Integer insert(ItemDataEntity itemDataEntity);
	List<ItemDataEntity> index(String owner);
	Optional<ItemDataEntity> show(String id);
	Integer update(ItemDataEntity itemDataEntity);
	Integer delete(String idItem);
}
