package com.familytree.jpa;

import org.springframework.stereotype.Repository;

import com.familytree.common.jpa.manager.Manager;
import com.familytree.entity.Marry;
import com.familytree.filter.MarryFilter;

@Repository
public interface MarryManager extends Manager<Marry, MarryFilter, Long> {

}
