package com.familytree.detail;

import java.util.List;

import com.familytree.entity.Marry;
import com.familytree.entity.Person;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class PersonDetail extends Person {
	private List<Person> childrens;
	private List<Marry> marries;
	
	public PersonDetail(Person p) {
		this.setId(p.getId());
		this.setName(p.getName());
		this.setSex(p.getSex());
		this.setBirthday(p.getBirthday());
		this.setDeadday(p.getDeadday());
		this.setAlive(p.isAlive());
		this.setRemark(p.getRemark());
		this.setFather(p.getFather());
		this.setMother(p.getMother());
		this.setFamily(p.getFamily());
	}
	
}
