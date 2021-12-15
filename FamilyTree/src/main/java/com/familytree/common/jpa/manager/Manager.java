package com.familytree.common.jpa.manager;

import java.util.List;

public interface Manager<Entity,Filter,Pk> {
	void saveOrUpdate(Entity entity);
	int delete(Pk pk);
	Entity getOne(Pk pk);
	List<Entity> findByFilter(Filter ...filters);
	List<Entity> findByFilterOnePage(Filter filter, int pageNo, int pageSize);
	
	long totalByFilter(Filter filter);
}
