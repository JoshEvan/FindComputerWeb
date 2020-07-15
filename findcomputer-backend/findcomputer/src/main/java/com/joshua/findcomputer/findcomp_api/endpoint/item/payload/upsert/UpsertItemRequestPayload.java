package com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert;

import lombok.Getter;

import java.math.BigDecimal;

public class UpsertItemRequestPayload {
	@Getter private String name, description, owner, category;
	@Getter private BigDecimal priceAmount;

	public UpsertItemRequestPayload setName(String name) {
		this.name = name;return this;
	}

	public UpsertItemRequestPayload setDescription(String description) {
		this.description = description;return this;
	}

	public UpsertItemRequestPayload setCategoryId(String category) {
		this.category = category;return this;
	}

	public UpsertItemRequestPayload setOwner(String owner) {
		this.owner = owner;return this;
	}

	public UpsertItemRequestPayload setPriceAmount(BigDecimal priceAmount) {
		this.priceAmount = priceAmount;return this;
	}
}
