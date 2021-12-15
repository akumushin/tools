package com.familytree.common.enums;

public enum Sex {
	MALE ("male"),
	FEMALE ("female"),
	UNKNOWN("unknown")
	;

	private String name;
	Sex(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
}
