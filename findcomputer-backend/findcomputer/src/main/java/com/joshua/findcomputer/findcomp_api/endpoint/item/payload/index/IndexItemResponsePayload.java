package com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index;

import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import lombok.Getter;

import java.util.List;

public class IndexItemResponsePayload extends ResponsePayload {
	@Getter List<Item> items;
	public IndexItemResponsePayload setItems(List<Item> items) {
		this.items = items;return this;
	}
}
