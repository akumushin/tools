package com.familytree.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class RelativePk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Person me;
	private Person you;
}
