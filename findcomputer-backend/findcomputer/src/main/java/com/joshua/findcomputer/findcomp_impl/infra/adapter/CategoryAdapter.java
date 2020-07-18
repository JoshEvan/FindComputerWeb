package com.joshua.findcomputer.findcomp_impl.infra.adapter;

import com.joshua.findcomputer.findcomp_api.model.Category;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.CategoryDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryAdapter {
	public static List<Category> convertDataEntitiesToModels(List<CategoryDataEntity> categoryDataEntities){
		return categoryDataEntities.stream()
			.map(
				categoryDataEntity -> new Category(categoryDataEntity.getName())
			).collect(Collectors.toList());
	}
	public static CategoryDataEntity convertResultSetToDataEntity(ResultSet resultSet){
		try{
			return new CategoryDataEntity().setName(resultSet.getString(CategoryDataEntity.NAME));
		}catch (SQLException throwable){
			return null;
		}
	}
}
