package com.familytree.service;

import java.util.List;

import com.familytree.common.page.PageData;
import com.familytree.entity.Relative;
import com.familytree.entity.RelativePk;
import com.familytree.filter.RelativeFilter;

public interface RelativeService {
	void saveOrUpdate(Relative entity);
	void delete(RelativePk id);
	PageData<Relative> getOnePageByFilter(RelativeFilter filter, int pageNo, int pageSize);
	List<Relative> getAllByFilter(RelativeFilter filter);
	Relative getOneById(RelativePk pk);
}
