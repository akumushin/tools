package com.familytree.filter;

import com.familytree.common.enums.Relationship;

import lombok.Data;

@Data
public class RelativeFilter {
	private PersonFilter me;
	private PersonFilter you;
	private Relationship relationship ;
}
