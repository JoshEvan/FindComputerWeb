package com.joshua.findcomputer.findcomp_api.endpoint.user.payload;

import lombok.Getter;

public class UserUpdateProfileRequestPayload extends UserProfileRequestPayload{
	@Getter private String newPassword;

	public UserUpdateProfileRequestPayload setNewPassword(String newPassword) {
		this.newPassword = newPassword;return this;
	}

}
