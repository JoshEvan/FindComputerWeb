package com.joshua.findcomputer.findcomp_impl.endpoint.items;

import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.ItemAPIController;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;

import javax.validation.constraints.NotNull;

public class ItemAPIControllerImpl implements ItemAPIController {
	@Override
	public ResponsePayload insertItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
		return null;
	}

	@Override
	public IndexItemResponsePayload indexItem(@NotNull IndexItemRequestPayload indexItemRequestPayload) {
		return null;
	}

	@Override
	public Item showIndex(@NotNull String id) {
		return null;
	}

	@Override
	public ResponsePayload updateItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
		return null;
	}

	@Override
	public ResponsePayload deleteItem(@NotNull String id) {
		return null;
	}
}
