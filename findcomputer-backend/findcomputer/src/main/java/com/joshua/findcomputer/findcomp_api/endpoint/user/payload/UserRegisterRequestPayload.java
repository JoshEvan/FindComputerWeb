package com.joshua.findcomputer.findcomp_api.endpoint.user.payload;

import lombok.Getter;

public class UserRegisterRequestPayload {
	@Getter private String username, password;

	public UserRegisterRequestPayload setUsername(String username) {
		this.username = username;return this;
	}

	public UserRegisterRequestPayload setPassword(String password) {
		this.password = password;return this;
	}
}
