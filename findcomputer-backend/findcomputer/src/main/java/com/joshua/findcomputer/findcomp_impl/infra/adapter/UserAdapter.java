package com.joshua.findcomputer.findcomp_impl.infra.adapter;

import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserAuthRegRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.User;
import com.joshua.findcomputer.findcomp_api.model.UserRole;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity.*;
import static com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity.OWNER;

public class UserAdapter {
	public static UserDataEntity convertRegisterPayloadToDataEntity(UserAuthRegRequestPayload userAuthRegRequestPayload){
		return new UserDataEntity()
			.setUsername(userAuthRegRequestPayload.getUsername())
			.setPassword(userAuthRegRequestPayload.getPassword());
	}

	public static UserDataEntity convertResultSetToDataEntity(ResultSet resultSet){
		try {
			return new UserDataEntity()
				.setUsername(resultSet.getString(UserDataEntity.USERNAME))
				.setPassword(resultSet.getString(UserDataEntity.PASSW));
		} catch (SQLException throwable) {
			return null;
		}
	}

	public static List<User> convertDataEntitiesToModels(List<UserDataEntity> userDataEntities){
		return userDataEntities.stream()
			.map(
				userDataEntity -> new User(
					UserRole.SUPERUSER.getGrantedAuthorities(),
					userDataEntity.getPassword(),
					userDataEntity.getUsername(),
					true,true,true,true
				)
			).collect(Collectors.toList());
	}
}
