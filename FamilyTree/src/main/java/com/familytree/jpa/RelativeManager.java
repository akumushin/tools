package com.familytree.jpa;

import org.springframework.stereotype.Repository;

import com.familytree.common.jpa.manager.Manager;
import com.familytree.entity.Relative;
import com.familytree.entity.RelativePk;
import com.familytree.filter.RelativeFilter;
@Repository
public interface RelativeManager extends Manager<Relative, RelativeFilter, RelativePk> {

}
