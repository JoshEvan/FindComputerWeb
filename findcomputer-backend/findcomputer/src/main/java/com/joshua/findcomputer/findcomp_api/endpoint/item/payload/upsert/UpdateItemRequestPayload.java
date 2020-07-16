package com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert;

import lombok.Getter;

public class UpdateItemRequestPayload extends InsertItemRequestPayload{
	@Getter private String itemId, requester;

	public UpdateItemRequestPayload setItemId(String itemId) {
		this.itemId = itemId;return this;
	}

	public UpdateItemRequestPayload setRequester(String requester) {
		this.requester = requester;return this;
	}
}
