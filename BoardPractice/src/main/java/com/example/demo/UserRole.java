package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN,ROLE_USER,ROLE_GUEST"),
	USER("ROLE_USER,ROLE_GUEST"),
    GUEST("ROLE_GUEST");

	private final String value;
}
