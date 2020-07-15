package com.joshua.findcomputer.findcomp_api.model;

import lombok.Getter;

import java.math.BigDecimal;

public class Item {
	@Getter private Integer id;
	@Getter private String name, description, category, price, owner;
	@Getter private BigDecimal priceAmount;

	public Item setId(Integer id) {
		this.id = id;return this;
	}

	public Item setName(String name) {
		this.name = name;return this;
	}

	public Item setDescription(String description) {
		this.description = description;return this;
	}

	public Item setCategory(String category) {
		this.category = category;return this;
	}

	public Item setPrice(String price) {
		this.price = price;return this;
	}

	public Item setOwner(String owner) {
		this.owner = owner;return this;
	}

	public Item setPriceAmount(BigDecimal priceAmount) {
		this.priceAmount = priceAmount;return this;
	}
}
