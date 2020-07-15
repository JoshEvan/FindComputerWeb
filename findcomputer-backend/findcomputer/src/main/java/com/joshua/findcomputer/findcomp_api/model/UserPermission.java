package com.joshua.findcomputer.findcomp_api.model;

import lombok.Getter;

public enum UserPermission {
	ITEM_READ("item:read"),
	ITEM_WRITE("item:write");

	@Getter
	private final String permission;
	UserPermission(String permission){ this.permission = permission; };

}
