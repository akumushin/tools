package com.familytree.form;

import javax.persistence.Column;

import com.familytree.common.form.Form;
import com.familytree.common.utils.ValueUtils;
import com.familytree.entity.Marry;
import com.familytree.entity.Person;
import com.familytree.filter.MarryFilter;
import com.familytree.filter.PersonFilter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@NoArgsConstructor
public class MarryForm implements Form<Marry, MarryFilter>{
	private String husbandId;
	private String wifeId;
	private String husbandName;
	private String wifeName;
	private String husbandFlag;
	private String wifeFlag;
	private String marryDate;
	private String divorceDate;
	private String divorced;
	private String remark;
	
	@Override
	public Marry toEntity() {
		return toEntity(new Marry());
	}

	@Override
	public Marry toEntity(Marry entity) {
		if(husbandId!=null && husbandId.length()>0)
			entity.setHusband(new Person(ValueUtils.toLong(husbandId)));
		if(wifeId!=null && wifeId.length()>0)
			entity.setWife(new Person(ValueUtils.toLong(wifeId)));
		if(husbandFlag!=null && husbandFlag.length()>0)
			entity.setHusbandFlag(ValueUtils.toInteger(husbandFlag));
		if(wifeFlag!=null && wifeFlag.length()>0)
			entity.setWifeFlag(ValueUtils.toInteger(wifeFlag));
		if(divorced!=null && divorced.length()>0)
			entity.setDivorced(ValueUtils.toBoolean(divorced));
		if(marryDate!=null && marryDate.length()>0)
			entity.setMarryDate(ValueUtils.toInteger(marryDate));
		if(divorceDate!=null && divorceDate.length()>0)
			entity.setDivorceDate(ValueUtils.toInteger(divorceDate));
		entity.setRemark(remark);
		return entity;
	}

	@Override
	public MarryFilter toFilter() {
		MarryFilter entity = new MarryFilter();
		if(husbandId!=null && husbandId.length()>0)
			entity.setHusband(new PersonFilter(ValueUtils.toLong(husbandId)));
		if(wifeId!=null && wifeId.length()>0)
			entity.setWife(new PersonFilter(ValueUtils.toLong(wifeId)));
		if(husbandFlag!=null && husbandFlag.length()>0)
			entity.setHusbandFlag(ValueUtils.toInteger(husbandFlag));
		if(wifeFlag!=null && wifeFlag.length()>0)
			entity.setWifeFlag(ValueUtils.toInteger(wifeFlag));
		if(divorced!=null && divorced.length()>0)
			entity.setDivorced(ValueUtils.toBoolean(divorced));
		if(marryDate!=null && marryDate.length()>0)
			entity.setMarryDate(ValueUtils.toInteger(marryDate));
		if(divorceDate!=null && divorceDate.length()>0)
			entity.setDivorceDate(ValueUtils.toInteger(divorceDate));
		return entity;
	}
	
}
