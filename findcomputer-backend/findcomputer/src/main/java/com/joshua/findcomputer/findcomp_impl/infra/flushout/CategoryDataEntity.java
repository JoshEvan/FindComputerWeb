package com.joshua.findcomputer.findcomp_impl.infra.flushout;

import lombok.Getter;
import lombok.Setter;

public class CategoryDataEntity extends FindcompDataEntity{
	@Getter private String name;
	public static String NAME = "name";

	public CategoryDataEntity() {
		TABLE="categories";
		numColumns=2;
	}

	public CategoryDataEntity setName(String name) {
		this.name = name;return this;
	}
}
