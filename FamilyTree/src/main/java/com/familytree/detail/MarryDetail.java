package com.familytree.detail;

import java.util.List;

import com.familytree.entity.Marry;
import com.familytree.entity.Person;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MarryDetail extends Marry {
	
	private List<Person> commonChild;
	//private List<Person> stepChildOfWife;
	//private List<Person> stepChildOfHusband;
	public MarryDetail(Marry marry) {
		this.setId(marry.getId());
		this.setHusband(marry.getHusband());
		this.setWife(marry.getWife());
		this.setHusbandFlag(marry.getHusbandFlag());
		this.setWifeFlag(marry.getWifeFlag());
		this.setDivorced(marry.isDivorced());
		this.setMarryDate(marry.getMarryDate());
		this.setDivorceDate(marry.getDivorceDate());
		this.setRemark(marry.getRemark());
		
	}
}
