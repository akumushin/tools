package com.familytree.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytree.common.page.PageData;
import com.familytree.detail.FamilyDetail;
import com.familytree.entity.Family;
import com.familytree.filter.FamilyFilter;
import com.familytree.filter.PersonFilter;
import com.familytree.jpa.FamilyManager;
import com.familytree.jpa.PersonManager;
import com.familytree.service.FamilyService;
@Service
public class FamilyServiceImpl implements FamilyService {
	@Autowired FamilyManager manager;
	@Autowired PersonManager personManager;
	@Override
	public void saveOrUpdate(Family family) {
		manager.saveOrUpdate(family);
	}

	@Override
	public boolean delete(long id) {
		return manager.delete(id) >=1;
	}
	@Override
	public PageData<Family> getOnePageByFilter(FamilyFilter filter, int pageNo, int pageSize) {
		PageData<Family> pageData = new PageData<>();
		pageData.setData(manager.findByFilterOnePage(filter, pageNo, pageSize));
		pageData.setPageNo(pageNo);
		pageData.setPageSize(pageSize);
		pageData.setTotal(manager.totalByFilter(filter));
		pageData.setPageMax((int)(pageData.getTotal()/ pageData.getPageSize()));
		if (pageData.getTotal()% pageData.getPageSize() !=0)
			pageData.setPageMax(pageData.getPageMax()+1);
		return pageData;
	}

	@Override
	public List<Family> getAllByFilter(FamilyFilter filter) {
		return manager.findByFilter(filter);
	}

	@Override
	public Family getOneById(long id) {
		try {
			return manager.getOne(id);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public FamilyDetail getDetail(long id) {
		Family family= getOneById(id);
		if(family==null)
			return null;
		FamilyDetail detail = new FamilyDetail(family);
		PersonFilter filter= new PersonFilter();
		filter.setFamily(new FamilyFilter(id));
		detail.setMembers( personManager.findByFilter(filter));
		return detail;
	}

}
