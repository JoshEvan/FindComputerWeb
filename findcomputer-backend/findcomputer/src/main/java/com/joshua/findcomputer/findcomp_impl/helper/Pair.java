package com.joshua.findcomputer.findcomp_impl.helper;

import lombok.Getter;
import lombok.Setter;

public class Pair<K,V> {
	@Getter @Setter private K key;
	@Getter @Setter private V val;
	public Pair(K k, V v) {
		this.key = k;
		this.val = v;
	}
}
