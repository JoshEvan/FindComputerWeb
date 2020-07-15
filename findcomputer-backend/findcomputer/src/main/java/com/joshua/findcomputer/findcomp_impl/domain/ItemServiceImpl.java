package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.ItemService;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import org.springframework.data.util.Pair;

import java.util.List;

public class ItemServiceImpl implements ItemService {
	@Override
	public Pair<Boolean, List<String>> insert(UpsertItemRequestPayload upsertCustomerRequestPayload) {
		return null;
	}

	@Override
	public List<Item> index(IndexItemRequestPayload indexItemRequestPayload) {
		return null;
	}

	@Override
	public Item show(String id) {
		return null;
	}

	@Override
	public Pair<Boolean, List<String>> update(UpsertItemRequestPayload upsertCustomerRequestPayload) {
		return null;
	}

	@Override
	public Pair<Boolean, List<String>> delete(String itemCode) {
		return null;
	}

	@Override
	public void generateReport(IndexItemRequestPayload indexItemRequestPayload) {

	}
}
