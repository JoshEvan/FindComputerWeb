package com.joshua.findcomputer.findcomp_impl.infra.adapter;

import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity.*;

public class ItemAdapter {
	public static ItemDataEntity convertResultSetToDataEntity(ResultSet resultSet){
		try {
			return new ItemDataEntity()
				.setId(resultSet.getObject(ID, UUID.class))
				.setName(resultSet.getString(NAME))
				.setDescription(resultSet.getString(DES))
				.setPrice(resultSet.getBigDecimal(PRICE))
				.setCategory(resultSet.getString(CAT))
				.setOwner(resultSet.getString(OWNER));
		} catch (SQLException throwable) {
			return null;
		}
	}

	public static ItemDataEntity convertUpsertPayloadToDataEntity(UpsertItemRequestPayload upsertItemRequestPayload){
		return new ItemDataEntity()
			.setName(upsertItemRequestPayload.getName())
			.setDescription(upsertItemRequestPayload.getDescription())
			.setPrice(upsertItemRequestPayload.getPriceAmount())
			.setCategory(upsertItemRequestPayload.getCategory())
			.setOwner(upsertItemRequestPayload.getOwner());
	}

	public static List<Item> convertDataEntitiesToModels(List<ItemDataEntity> itemDataEntities){
		return itemDataEntities.stream()
			.map(
				dataEntity -> new Item()
				.setId(dataEntity.getId())
				.setName(dataEntity.getName())
				.setOwner(dataEntity.getOwner())
				.setDescription(dataEntity.getDescription())
				.setPriceAmount(dataEntity.getPrice())
				.setCategory(dataEntity.getCategory())
			).collect(Collectors.toList());
	}
}
