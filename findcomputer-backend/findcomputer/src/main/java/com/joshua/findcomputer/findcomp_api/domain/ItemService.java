package com.joshua.findcomputer.findcomp_api.domain;

import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.InsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpdateItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;


import java.util.List;

public interface ItemService {
	Pair<Boolean, List<String>> insert(InsertItemRequestPayload upsertCustomerRequestPayload);
	List<Item> index(IndexItemRequestPayload indexItemRequestPayload);
	Item show(String id);
	Pair<Boolean,List<String>> update(UpdateItemRequestPayload updateItemRequestPayload);
	Pair<Boolean,List<String>> delete(String id, String requester);
	Pair<Boolean,List<String>> buy(String id, String requester);
}
