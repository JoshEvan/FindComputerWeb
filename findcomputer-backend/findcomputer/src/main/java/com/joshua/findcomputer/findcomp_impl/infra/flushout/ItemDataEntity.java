package com.joshua.findcomputer.findcomp_impl.infra.flushout;

import lombok.Getter;

import java.math.BigDecimal;

public class ItemDataEntity extends FindcompDataEntity{
	@Getter private Integer id, categoryId;
	@Getter private String name, description, owner;
	@Getter private BigDecimal price;

	public static String
		ID = "id",
		NAME ="name",
		DES ="description",
		PRICE = "price",
		CAT_ID = "category_id",
		OWNER = "owner"
	;

	public ItemDataEntity() {
		TABLE="items";
		numColumns=6;
	}

	public ItemDataEntity setName(String name) {
		this.name = name;return this;
	}

	public ItemDataEntity setDescription(String description) {
		this.description = description;return this;
	}

	public ItemDataEntity setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;return this;
	}

	public ItemDataEntity setOwner(String owner) {
		this.owner = owner;return this;
	}

	public ItemDataEntity setPrice(BigDecimal price) {
		this.price = price;return this;
	}

	public ItemDataEntity setId(Integer id) {
		this.id = id;return this;
	}

}
