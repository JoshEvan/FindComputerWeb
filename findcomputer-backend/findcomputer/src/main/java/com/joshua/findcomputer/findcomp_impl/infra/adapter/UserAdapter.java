package com.joshua.findcomputer.findcomp_impl.infra.adapter;

import com.joshua.findcomputer.findcomp_api.endpoint.user.payload.UserRegisterRequestPayload;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.UserDataEntity;
import org.apache.catalina.User;

public class UserAdapter {
	public static UserDataEntity convertRegisterPayloadToDataEntity(UserRegisterRequestPayload userRegisterRequestPayload){
		return new UserDataEntity()
			.setUsername(userRegisterRequestPayload.getUsername())
			.setPassword(userRegisterRequestPayload.getPassword());
	}
}
