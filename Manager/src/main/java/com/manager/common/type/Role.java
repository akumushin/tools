package com.manager.common.type;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
	ADMIN,
	CUSTOMER,
	COMPANY_MANAGER,
	COMPANY_STAFF;

	@Override
	public String getAuthority() {
		return "ROLE_"+this.name();
	}
}
