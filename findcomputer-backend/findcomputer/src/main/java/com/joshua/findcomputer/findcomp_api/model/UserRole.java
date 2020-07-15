package com.joshua.findcomputer.findcomp_api.model;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum UserRole {
	SUPERUSER(Sets.newHashSet());

	@Getter private final Set<UserPermission> permissions;
	UserRole(Set<UserPermission> permissions) {
		this.permissions = permissions;
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
			.map(p -> new SimpleGrantedAuthority(p.getPermission()))
			.collect(Collectors.toSet());
		permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
		return permissions;
	}

}
