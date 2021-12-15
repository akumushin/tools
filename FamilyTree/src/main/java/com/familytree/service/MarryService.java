package com.familytree.service;

import java.util.List;

import com.familytree.common.page.PageData;
import com.familytree.detail.MarryDetail;
import com.familytree.entity.Marry;
import com.familytree.filter.MarryFilter;

public interface MarryService {
	void saveOrUpdate(Marry marry);
	boolean delete(long id);
	PageData<Marry> getOnePageByFilter(MarryFilter filter, int pageNo, int pageSize);
	List<Marry> getAllByFilter(MarryFilter ...filters);
	Marry getOneById(long id);
	MarryDetail getDetailById(long id);
}
