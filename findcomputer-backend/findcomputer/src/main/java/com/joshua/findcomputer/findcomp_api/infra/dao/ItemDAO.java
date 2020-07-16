package com.joshua.findcomputer.findcomp_api.infra.dao;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemDAO {
	Integer insert(ItemDataEntity itemDataEntity);
	List<ItemDataEntity> index(String owner, String category);
	Optional<ItemDataEntity> show(UUID id);
	Integer update(ItemDataEntity itemDataEntity);
	Integer delete(UUID idItem);
}
