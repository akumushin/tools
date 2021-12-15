package com.familytree.service;

import java.util.List;

import com.familytree.common.page.PageData;
import com.familytree.detail.FamilyDetail;
import com.familytree.entity.Family;
import com.familytree.filter.FamilyFilter;

public interface FamilyService {
	void saveOrUpdate(Family family);
	boolean delete(long id);
	PageData<Family> getOnePageByFilter(FamilyFilter filter, int pageNo, int pageSize);
	List<Family> getAllByFilter(FamilyFilter filter);
	Family getOneById(long id);
	FamilyDetail getDetail(long id);
}
