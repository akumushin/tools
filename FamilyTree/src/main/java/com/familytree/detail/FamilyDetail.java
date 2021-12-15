package com.familytree.detail;

import java.util.List;

import com.familytree.entity.Family;
import com.familytree.entity.Person;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class FamilyDetail extends Family {
	private List<Person> members;
	
	public FamilyDetail (Family family) {
		this.setId(family.getId());
		this.setPlace(family.getPlace());
		this.setName(family.getName());
	};
}
