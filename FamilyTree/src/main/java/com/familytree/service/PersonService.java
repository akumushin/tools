package com.familytree.service;

import com.familytree.entity.Person;
import com.familytree.filter.PersonFilter;

import java.util.List;

import com.familytree.common.page.*;
import com.familytree.detail.PersonDetail;
public interface PersonService {
	void saveOrUpdate(Person person);
	boolean delete(long id);
	PageData<Person> getOnePageByFilter(PersonFilter filter, int pageNo, int pageSize);
	List<Person> getAllByFilter(PersonFilter ...filters);
	Person getOneById(long id);
	PersonDetail getDetailById(long id);
}
