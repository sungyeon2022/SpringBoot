package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum UserRole {
	ADMIN("ROLE_ADMIN,ROLE_USER"),
	USER("ROLE_USER");

	private final String value;
}
