package com.joshua.findcomputer.findcomp_impl.infra.adapter;

import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserAuthRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserProfileRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserUpdateProfileRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.User;
import com.joshua.findcomputer.findcomp_api.model.UserRole;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity.*;

public class UserAdapter {
	public static UserDataEntity convertRegisterPayloadToDataEntity(UserProfileRequestPayload profileRequestPayload){
		return new UserDataEntity()
			.setUsername(profileRequestPayload.getUsername())
			.setPassword(profileRequestPayload.getPassword())
			.setProfileInfo(profileRequestPayload.getProfileInfo());
	}

	public static UserDataEntity convertUpdatePayloadToDataEntity(UserUpdateProfileRequestPayload profileRequestPayload){
		return new UserDataEntity()
			.setUsername(profileRequestPayload.getUsername())
			.setPassword(profileRequestPayload.getNewPassword())
			.setProfileInfo(profileRequestPayload.getProfileInfo());
	}

	public static UserDataEntity convertResultSetToDataEntity(ResultSet resultSet){
		try {
			return new UserDataEntity()
				.setUsername(resultSet.getString(USERNAME))
				.setPassword(resultSet.getString(PASSW))
				.setProfileInfo(resultSet.getString(PROFIL));
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
					userDataEntity.getProfileInfo(),
					true,true,true,true
				)
			).collect(Collectors.toList());
	}
}
