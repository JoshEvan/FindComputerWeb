package com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert;

import lombok.Getter;

import java.math.BigDecimal;

public class InsertItemRequestPayload {
	@Getter private String name, description, owner, category;
	@Getter private BigDecimal priceAmount;

	public InsertItemRequestPayload setName(String name) {
		this.name = name;return this;
	}

	public InsertItemRequestPayload setDescription(String description) {
		this.description = description;return this;
	}

	public InsertItemRequestPayload setCategoryId(String category) {
		this.category = category;return this;
	}

	public InsertItemRequestPayload setOwner(String owner) {
		this.owner = owner;return this;
	}

	public InsertItemRequestPayload setPriceAmount(BigDecimal priceAmount) {
		this.priceAmount = priceAmount;return this;
	}
}
