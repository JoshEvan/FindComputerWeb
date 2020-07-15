package com.joshua.findcomputer.findcomp_impl.endpoint.item;

import com.joshua.findcomputer.findcomp_api.domain.ItemService;
import com.joshua.findcomputer.findcomp_api.endpoint.ResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.ItemAPIController;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemResponsePayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
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
	public ResponsePayload insertItem(@NotNull UpsertItemRequestPayload upsertItemRequestPayload) {
		Pair<Boolean, List<String>> res = itemService.insert(upsertItemRequestPayload);
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
