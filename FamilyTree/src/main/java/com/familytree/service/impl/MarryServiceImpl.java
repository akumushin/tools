package com.familytree.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytree.common.page.PageData;
import com.familytree.detail.MarryDetail;
import com.familytree.entity.Marry;
import com.familytree.filter.MarryFilter;
import com.familytree.filter.PersonFilter;
import com.familytree.jpa.MarryManager;
import com.familytree.jpa.PersonManager;
import com.familytree.service.MarryService;
@Service
public class MarryServiceImpl implements MarryService {
	@Autowired MarryManager manager; 
	@Autowired PersonManager personManager;
	@Override
	public void saveOrUpdate(Marry marry) {
		manager.saveOrUpdate(marry); 
	}

	@Override
	public boolean delete(long pk) {
		int a= manager.delete(pk);
		System.out.println(a);
		return a>=1;
	}

	@Override
	public PageData<Marry> getOnePageByFilter(MarryFilter filter, int pageNo, int pageSize) {
		PageData<Marry> pageData =new PageData<Marry>();
		pageData.setData( manager.findByFilterOnePage(filter, pageNo, pageSize));
		pageData.setTotal(manager.totalByFilter(filter));
		pageData.setPageNo(pageNo);
		pageData.setPageSize(pageSize);
		pageData.setPageMax((int)(pageData.getTotal() /pageSize));
		if(pageData.getTotal() % pageSize ==0 )
			pageData.setPageMax(pageData.getPageMax()+1);
		return pageData;
	}

	@Override
	public List<Marry> getAllByFilter(MarryFilter ...filters) {
		return manager.findByFilter(filters);
	}

	@Override
	public Marry getOneById(long id) {
		try {
			return manager.getOne(id);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public MarryDetail getDetailById(long id) {
		Marry marry = getOneById(id);
		if(marry==null)
			return null;
		MarryDetail detail= new MarryDetail(marry);
		PersonFilter filter = new PersonFilter();
		filter.setFather(new PersonFilter(marry.getHusband().getId()));
		filter.setMother(new PersonFilter(marry.getWife().getId()));
		detail.setCommonChild(personManager.findByFilter(filter));
		
		return detail;
	}

}
