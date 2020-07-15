package com.joshua.findcomputer.findcomp_impl.infra.flushout;

import lombok.Getter;

public class UserDataEntity extends FindcompDataEntity{
	@Getter private String username, password;
	public static String
		USERNAME = "username",
		PASSW ="password"
	;
	public UserDataEntity() {
		TABLE="users";
		numColumns=2;
	}

	public UserDataEntity setUsername(String username) {
		this.username = username;return this;
	}

	public UserDataEntity setPassword(String password) {
		this.password = password;return this;
	}
}
