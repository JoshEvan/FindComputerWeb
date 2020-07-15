package com.joshua.findcomputer.findcomp_api.domain;

import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import org.springframework.data.util.Pair;


import java.util.List;

public interface ItemService {
	Pair<Boolean, List<String>> insert(UpsertItemRequestPayload upsertCustomerRequestPayload);
	List<Item> index(IndexItemRequestPayload indexItemRequestPayload);
	Item show(String id);
	Pair<Boolean,List<String>> update(UpsertItemRequestPayload upsertCustomerRequestPayload);
	Pair<Boolean,List<String>> delete(String itemCode);
	void generateReport(IndexItemRequestPayload indexItemRequestPayload);
}
