package com.familytree.jpa;

import org.springframework.stereotype.Repository;

import com.familytree.common.jpa.manager.Manager;
import com.familytree.entity.Family;
import com.familytree.filter.FamilyFilter;

@Repository
public interface FamilyManager extends Manager<Family, FamilyFilter, Long> {

}
