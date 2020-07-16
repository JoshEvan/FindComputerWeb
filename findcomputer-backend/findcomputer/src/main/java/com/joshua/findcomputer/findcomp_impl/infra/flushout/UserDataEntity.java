package com.joshua.findcomputer.findcomp_impl.infra.flushout;

import lombok.Getter;

public class UserDataEntity extends FindcompDataEntity{
	@Getter private String username, password, profileInfo;
	public static String
		USERNAME = "username",
		PASSW ="password",
		PROFIL = "profile_info"
	;
	public UserDataEntity() {
		TABLE="users";
		numColumns=3;
	}

	public UserDataEntity setUsername(String username) {
		this.username = username;return this;
	}

	public UserDataEntity setPassword(String password) {
		this.password = password;return this;
	}

	public UserDataEntity setProfileInfo(String profileInfo) {
		this.profileInfo = profileInfo;return this;
	}
}
