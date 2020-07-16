package com.joshua.findcomputer.findcomp_impl.domain;

import com.joshua.findcomputer.findcomp_api.domain.ItemService;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index.IndexItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.InsertItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.endpoint.item.payload.upsert.UpdateItemRequestPayload;
import com.joshua.findcomputer.findcomp_api.infra.dao.ItemDAO;
import com.joshua.findcomputer.findcomp_api.model.Item;
import com.joshua.findcomputer.findcomp_impl.helper.Pair;
import com.joshua.findcomputer.findcomp_impl.infra.flushout.ItemDataEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.joshua.findcomputer.findcomp_impl.helper.Helper.*;
import static com.joshua.findcomputer.findcomp_impl.infra.adapter.ItemAdapter.*;

@Component("itemV1Service")
@Service
public class ItemServiceImpl implements ItemService {

	private final ItemDAO itemDAO;
	private final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	public ItemServiceImpl(@Qualifier("pgItem") ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}

	@Override
	public Pair<Boolean, List<String>> insert(InsertItemRequestPayload insertItemRequestPayload) {
		Integer stat = itemDAO.insert(
			convertInsertPayloadToDataEntity(insertItemRequestPayload).setId(UUID.randomUUID())
		);
		return new Pair<>(
			(stat == 1),
			(stat == 1 ?
				Collections.singletonList(ITEM+ insertItemRequestPayload.getName()+ SUCCESS+" "+ INSERTED)
				: Collections.singletonList(ITEM+ insertItemRequestPayload.getName()+ FAIL+" "+ INSERTED)
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
		ItemDataEntity dataEntity = itemDAO.show(UUID.fromString(id)).orElse(null);
		if(dataEntity == null) return null;
		return convertDataEntitiesToModels(Collections.singletonList(dataEntity)).get(0);
	}

	@Override
	public Pair<Boolean, List<String>> update(UpdateItemRequestPayload updateItemRequestPayload) {
		Item item = show(updateItemRequestPayload.getItemId());
		if(item == null){
			return new Pair<>(
				false,
				Collections.singletonList(
					ITEM+updateItemRequestPayload.getItemId()+NOTFOUND)
			);
		}
		logger.info(item.getOwner()+" == "+updateItemRequestPayload.getRequester());
		if(!item.getOwner().equals(updateItemRequestPayload.getRequester())){
			logger.info(item.getOwner()+" attempt to update item of user "+updateItemRequestPayload.getRequester());
			return new Pair<>(
				false,
				Collections.singletonList(
					USER+updateItemRequestPayload.getRequester()+" IS NOT ALLOWED TO UPDATE "+updateItemRequestPayload.getName())
			);
		}
		Integer stat = itemDAO.update(convertUpdatePayloadToDataEntity(updateItemRequestPayload));
		return new Pair<>(
			(stat == 1),
			Collections.singletonList(
				ITEM+ updateItemRequestPayload.getName()+(stat == 1 ? SUCCESS : FAIL)+UPDATED)
		);
	}

	@Override
	public Pair<Boolean, List<String>> delete(String id, String requester) {
		Item item = show(id);
		if(item == null){
			return new Pair<>(
			false,
				Collections.singletonList(
					ITEM+id+NOTFOUND)
			);
		}
		if(!item.getOwner().equals(requester)){
			return new Pair<>(
				false,
				Collections.singletonList(
					USER+requester+" IS NOT ALLOWED TO DELETE "+item.getName())
			);
		}
		Integer stat = itemDAO.delete(UUID.fromString(id));
		return new Pair<>(
			(stat == 1),
			Collections.singletonList(
				ITEM+id+(stat == 1 ? SUCCESS : FAIL)+DELETED)
		);
	}

}
