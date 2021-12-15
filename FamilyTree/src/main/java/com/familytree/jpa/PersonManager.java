package com.familytree.jpa;

import com.familytree.common.jpa.manager.Manager;
import com.familytree.entity.Person;
import com.familytree.filter.PersonFilter;

public interface PersonManager extends Manager<Person, PersonFilter, Long> {
	
}
