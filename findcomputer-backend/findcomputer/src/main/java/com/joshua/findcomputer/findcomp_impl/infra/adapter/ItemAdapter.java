package com.joshua.findcomputer.findcomp_impl.infra.adapter;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity.*;

public class ItemAdapter {
	public static ItemDataEntity convertResultSetToDataEntity(ResultSet resultSet){
		try {
			return new ItemDataEntity()
				.setId(resultSet.getInt(ID))
				.setName(resultSet.getString(NAME))
				.setDescription(resultSet.getString(DES))
				.setPrice(resultSet.getBigDecimal(PRICE))
				.setCategoryId(resultSet.getInt(CAT_ID))
				.setOwner(resultSet.getString(OWNER));
		} catch (SQLException throwable) {
			return null;
		}
	}
}
