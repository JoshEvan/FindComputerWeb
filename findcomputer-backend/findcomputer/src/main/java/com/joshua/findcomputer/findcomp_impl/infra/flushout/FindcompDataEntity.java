package com.joshua.findcomputer.findcomp_impl.infra.flushout;

import lombok.Getter;

public abstract class FindcompDataEntity {
	public final String SCHEMA = "findcomp_user,public";
	public String TABLE;
	public int numColumns;
	public static String ISACTIVE = "is_active";
	@Getter public Boolean isActive;
	public FindcompDataEntity setActive(Boolean active) {
		isActive = active;
		return this;
	}
}
