package com.familytree.common.page;

import java.util.List;

import lombok.Data;

@Data
public class PageData<T> {
	private int pageSize;
	private int pageNo;
	private int pageMax;
	private long total;
	private List<T> data;
}
