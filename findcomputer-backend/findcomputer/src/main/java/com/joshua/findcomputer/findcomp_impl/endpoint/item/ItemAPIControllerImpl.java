package com.joshua.findcomputer.findcomp_impl.endpoint.item;

import com.joshua.findcomputer.findcomp_api.domain.ItemService;
import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.ItemAPIController;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.InsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpdateItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.model.Item;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

import static com.joshua.findcomputer.findcomp_impl.helper.HttpStatus.*;

@Component("itemAPIV1")
public class ItemAPIControllerImpl implements ItemAPIController {
	private final ItemService itemService;

	@Autowired
	public ItemAPIControllerImpl(@Qualifier("itemV1Service") ItemService itemService) {
		this.itemService = itemService;
	}

	@Override
	public ResponsePayload insertItem(@NotNull InsertItemRequestPayload insertItemRequestPayload) {
		Pair<Boolean, List<String>> res = itemService.insert(insertItemRequestPayload);
		return new ResponsePayload()
			.setStatus((res.getKey() ? SUCCESS : FAIL).toString())
			.setMessage(res.getVal());
	}

	@Override
	public IndexItemResponsePayload indexItem(@NotNull IndexItemRequestPayload indexItemRequestPayload) {
		return new IndexItemResponsePayload()
			.setItems(itemService.index(indexItemRequestPayload));
	}

	@Override
	public Item showIndex(@NotNull String id) {
		return itemService.show(id);
	}

	@Override
	public ResponsePayload updateItem(@NotNull UpdateItemRequestPayload updateItemRequestPayload) {
		Pair<Boolean, List<String>> resp = itemService.update(updateItemRequestPayload);
		return new ResponsePayload()
			.setMessage(resp.getVal())
			.setStatus(resp.getKey() ? SUCCESS.toString() : FAIL.toString());
	}

	@Override
	public ResponsePayload deleteItem(@NotNull String id, @NotNull String requester) {
		Pair<Boolean, List<String>> resp = itemService.delete(id, requester);
		return new ResponsePayload()
			.setMessage(resp.getVal())
			.setStatus(resp.getKey() ? SUCCESS.toString() : FAIL.toString());
	}
}
