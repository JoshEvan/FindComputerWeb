package com.joshua.findcomputer.findcomp_impl.infra.flushout;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemDataEntity extends FindcompDataEntity{
	@Getter private UUID id;
	@Getter private String name, description, owner, category;
	@Getter private BigDecimal price;

	public static String
		ID = "id",
		NAME ="name",
		DES ="description",
		PRICE = "price",
		CAT = "category",
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

	public ItemDataEntity setCategory(String category) {
		this.category = category;return this;
	}

	public ItemDataEntity setOwner(String owner) {
		this.owner = owner;return this;
	}

	public ItemDataEntity setPrice(BigDecimal price) {
		this.price = price;return this;
	}

	public ItemDataEntity setId(UUID id) {
		this.id = id;return this;
	}

}
