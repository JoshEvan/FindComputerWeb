package com.joshua.findcomputer.findcomp_api.endpoint.item.payload.index;

import lombok.Getter;
import lombok.Setter;

public class IndexItemRequestPayload {
	/**
	 * if owner != "" -> user see the `owner`'s items in shop
	 * else -> user see all items in shop
	 */
	@Getter @Setter private String owner;
}
