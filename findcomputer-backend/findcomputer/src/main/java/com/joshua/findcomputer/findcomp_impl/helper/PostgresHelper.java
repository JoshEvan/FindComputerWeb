package com.joshua.findcomputer.findcomp_impl.helper;

import com.joshua.findcomputer.findcomp_impl.infra.flushout.FindcompDataEntity;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostgresHelper {
	public static String insertOperation(FindcompDataEntity dataEntity){
		String sql = "INSERT INTO "
			+ dataEntity.TABLE
			+ " VALUES ( ";
		int j = 0;
		for (Field field : dataEntity.getClass().getDeclaredFields()) {
			//field.setAccessible(true); // if you want to modify private fields
			if(j >= dataEntity.numColumns) break;
			if(j > 0) sql+=", ";
			if(field.getType() == Date.class){
				sql+="?::date";
			}else{
				sql+="?";
			}
			j++;

		}
		sql+=" )";
		return sql;
	}

	public static String selectOperation(FindcompDataEntity dataEntity){
		String sql = "SELECT * FROM "
			+ dataEntity.TABLE;
		return sql;
	}

	public static String updateOperation(FindcompDataEntity dataEntity, HashMap<String,Object> params, String comparator){
		String sql = "UPDATE "
			+ dataEntity.TABLE
			+ " SET";
		int i = 0;
		for(Map.Entry param : params.entrySet()){
			if(i++ > 0)  sql+=",";
			sql+=" "+param.getKey()+" = \'"+param.getValue()+"\'";
		}
		sql+=" WHERE "+comparator;
		return sql;
	}

	public static String deleteOperation(FindcompDataEntity dataEntity, String condition){
		return "DELETE FROM "+dataEntity.TABLE+ " "+condition;
	}

}
