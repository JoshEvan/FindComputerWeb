package com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index;

import lombok.Getter;
import lombok.Setter;

public class IndexItemRequestPayload {
	/**
	 * if isOwned == true -> user see their own items in shop
	 * else -> user see all items in shop
	 */
	@Getter @Setter private Boolean isOwned;
}
