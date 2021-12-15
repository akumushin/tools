package com.familytree.form;


import org.springframework.validation.annotation.Validated;

import com.familytree.common.form.Form;
import com.familytree.common.utils.ValueUtils;
import com.familytree.entity.Family;
import com.familytree.filter.FamilyFilter;
import com.familytree.filter.PersonFilter;

import lombok.Data;

@Data
@Validated
public class FamilyForm implements Form<Family, FamilyFilter>{
	private String id;
	private String place;
	private String name;
	private String memberId;
	@Override
	public Family toEntity() {
		Family family = new Family();
		return toEntity(family);
	}
	@Override
	public Family toEntity(Family entity) {
		entity.setPlace(place);
		entity.setName(name);
		return entity;
	}
	@Override
	public FamilyFilter toFilter() {
		FamilyFilter filter= new FamilyFilter();
		filter.setId(ValueUtils.toLong(id));
		filter.setPlace(place);
		filter.setName(name);
		Long memberId= ValueUtils.toLong(this.memberId);
		System.out.println(memberId+"-"+ this.memberId);
		if(memberId!=null)
			filter.setMember(new PersonFilter(memberId));
		return filter;
	}
	
}
