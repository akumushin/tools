package com.familytree.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytree.common.page.PageData;
import com.familytree.detail.PersonDetail;
import com.familytree.entity.Person;
import com.familytree.filter.MarryFilter;
import com.familytree.filter.PersonFilter;
import com.familytree.jpa.MarryManager;
import com.familytree.jpa.PersonManager;
import com.familytree.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {
	@Autowired PersonManager manager;
	@Autowired MarryManager marryManager;
	
	@Override
	public void saveOrUpdate(Person person) {
		manager.saveOrUpdate(person);
	}

	@Override
	public boolean delete(long id) {
		return manager.delete(id)>=1;
	}

	@Override
	public PageData<Person> getOnePageByFilter(PersonFilter filter, int pageNo, int pageSize) {
		PageData<Person> pageData =new PageData<Person>();
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
	public List<Person> getAllByFilter(PersonFilter ...filters) {
		return manager.findByFilter(filters);
	}

	@Override
	public Person getOneById(long id) {
		try {
			return manager.getOne(id);
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public PersonDetail getDetailById(long id) {
		Person person = getOneById(id);
		if(person == null)
			return null;
		PersonDetail detail = new PersonDetail(person);
		PersonFilter[] childFilters = new PersonFilter[2];
		childFilters[0] = new PersonFilter();
		childFilters[1] =new PersonFilter();
		childFilters[0].setFather(new PersonFilter(person.getId()));
		childFilters[1].setMother(new PersonFilter(person.getId()));
		detail.setChildrens(manager.findByFilter(childFilters));
		
		MarryFilter[] marryFilter = new MarryFilter[2];
		marryFilter[0] = new MarryFilter();
		marryFilter[1] =new MarryFilter();
		marryFilter[0].setHusband(new PersonFilter(person.getId()));
		marryFilter[1].setWife(new PersonFilter(person.getId()));
		detail.setMarries(marryManager.findByFilter(marryFilter));
		return detail;
	}

}
