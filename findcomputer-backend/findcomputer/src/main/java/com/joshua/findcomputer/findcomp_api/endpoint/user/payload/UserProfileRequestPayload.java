package com.joshua.findcomputer.findcomp_api.endpoint.user.payload;

import lombok.Getter;

public class UserProfileRequestPayload {
	@Getter
	private String username, password, profileInfo;

	public UserProfileRequestPayload setUsername(String username) {
		this.username = username;return this;
	}

	public UserProfileRequestPayload setPassword(String password) {
		this.password = password;return this;
	}

	public UserProfileRequestPayload setProfileInfo(String profileInfo) {
		this.profileInfo = profileInfo;return this;
	}

}
