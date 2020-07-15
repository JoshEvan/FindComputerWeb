package com.joshua.findcomputer.findcomp_api.endpoint.user.payload;

import lombok.Getter;

public class UserAuthRegRequestPayload {
	@Getter private String username, password;

	public UserAuthRegRequestPayload setUsername(String username) {
		this.username = username;return this;
	}

	public UserAuthRegRequestPayload setPassword(String password) {
		this.password = password;return this;
	}
}
