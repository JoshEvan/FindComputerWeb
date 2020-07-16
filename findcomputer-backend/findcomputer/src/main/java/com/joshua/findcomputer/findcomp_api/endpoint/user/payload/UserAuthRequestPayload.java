package com.joshua.findcomputer.findcomp_api.endpoint.user.payload;

import lombok.Getter;

public class UserAuthRequestPayload {
	@Getter private String username, password;

	public UserAuthRequestPayload setUsername(String username) {
		this.username = username;return this;
	}

	public UserAuthRequestPayload setPassword(String password) {
		this.password = password;return this;
	}
}
