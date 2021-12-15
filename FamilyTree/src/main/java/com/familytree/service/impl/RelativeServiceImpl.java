package com.familytree.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.familytree.common.exception.NotFoundException;
import com.familytree.common.page.PageData;
import com.familytree.entity.Relative;
import com.familytree.entity.RelativePk;
import com.familytree.filter.RelativeFilter;
import com.familytree.jpa.RelativeManager;
import com.familytree.service.RelativeService;

@Service
public class RelativeServiceImpl implements RelativeService {
	@Autowired RelativeManager manager;
	@Override
	public void saveOrUpdate(Relative entity) {
		manager.saveOrUpdate(entity);
	}

	@Override
	public void delete(RelativePk id) {
		if( manager.delete(id)<1)
			throw new NotFoundException("Not found relativePk =" +id);
	}

	@Override
	public PageData<Relative> getOnePageByFilter(RelativeFilter filter, int pageNo, int pageSize) {
		PageData<Relative> pageData =new PageData<Relative>();
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
	public List<Relative> getAllByFilter(RelativeFilter filter) {
		return manager.findByFilter(filter);
	}

	@Override
	public Relative getOneById(RelativePk pk) {
		try {
			return manager.getOne(pk);
		}catch(Exception e) {
			return null;
		}
	}

}
