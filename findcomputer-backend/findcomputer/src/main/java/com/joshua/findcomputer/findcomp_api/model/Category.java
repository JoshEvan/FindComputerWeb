package com.joshua.findcomputer.findcomp_api.model;

import lombok.Getter;
import lombok.Setter;

public class Category {
	@Getter
	@Setter private String name;

	public Category(String name) {
		this.name = name;
	}
}
