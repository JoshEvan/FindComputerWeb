package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.ItemService;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.infra.dao.ItemDAO;
import com.joshua.findcomputer.findcomp_api.model.Item;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.joshua.findcomputer.findcomp_impl.helper.Helper.*;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.ItemAdapter.convertDataEntitiesToModels;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.ItemAdapter.convertUpsertPayloadToDataEntity;

@Component("itemV1Service")
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemDAO itemDAO;

	@Autowired
	public ItemServiceImpl(@Qualifier("pgItem") ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Override
	public Pair<Boolean, List<String>> insert(UpsertItemRequestPayload upsertItemRequestPayload) {
		Integer stat = itemDAO.insert(
			convertUpsertPayloadToDataEntity(upsertItemRequestPayload).setId(UUID.randomUUID())
		);
		return new Pair<>(
			(stat == 1),
			(stat == 1 ?
				Collections.singletonList(ITEM+ upsertItemRequestPayload.getName()+ SUCCESS+" "+ INSERTED)
				: Collections.singletonList(ITEM+ upsertItemRequestPayload.getName()+ FAIL+" "+ INSERTED)
			)
		);
	}

	@Override
	public List<Item> index(IndexItemRequestPayload indexItemRequestPayload) {
		List<ItemDataEntity> dataEntities = itemDAO.index(
			indexItemRequestPayload.getOwner());
		List<Item> items = convertDataEntitiesToModels(dataEntities);
		items.stream()
			.map(
				item -> item
					.setPrice(formatCurrency(item.getPriceAmount()))
			).collect(Collectors.toList());
		return items;
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
