package com.familytree.form;


import javax.validation.constraints.NotNull;

import com.familytree.common.form.Form;
import com.familytree.common.utils.ValueUtils;
import com.familytree.entity.Family;
import com.familytree.entity.Person;
import com.familytree.filter.FamilyFilter;
import com.familytree.filter.PersonFilter;

import lombok.Data;
@Data
public class PersonForm implements Form<Person,PersonFilter> {
	private String id;
	private String name;
	private String sex;
	private String alive;
	private String birthday;
	private String deadday;
	private String fatherId;
	private String motherId;
	private String familyId;
	private String remark;
	
	public Person toEntity() {
		return toEntity(new Person());
	}
	public Person toEntity(Person person) {
		if(name !=null && name.length()>0)
			person.setName(name);
		if(sex!=null && sex.length()>0)
			person.setSex(ValueUtils.toSex(sex));
		if(alive!=null && alive.length()>0)
			person.setAlive(ValueUtils.toBoolean(alive));
		if(birthday!=null && birthday.length()>0)
			person.setBirthday(ValueUtils.toInteger(birthday));
		if(deadday!=null && deadday.length()>0)
			person.setDeadday(ValueUtils.toInteger(deadday));
		if(fatherId!=null && fatherId.length()>0)
			person.setFather(new Person(ValueUtils.toLong(fatherId)));
		else
			person.setFather(null);
		if(motherId!=null && motherId.length()>0)
			person.setMother(new Person(ValueUtils.toLong(motherId)));
		else
			person.setMother(null);
		if(familyId!=null&& familyId.length()>0)
			person.setFamily(new Family(ValueUtils.toLong(familyId)));
		else
			person.setFamily(null);
		if(remark!=null)
			person.setRemark(remark);
		return person;
	}
	
	public PersonFilter toFilter() {
		PersonFilter person = new PersonFilter();
		if(id!=null && id.length()>0)
			person.setId(ValueUtils.toLong(id));
		if(name !=null && name.length()>0)
			person.setName(name);
		if(sex!=null && sex.length()>0)
			person.setSex(ValueUtils.toSex(sex));
		if(alive!=null && alive.length()>0)
			person.setAlive(ValueUtils.toBoolean(alive));
		if(birthday!=null && birthday.length()>0)
			person.setBirthday(ValueUtils.toInteger(birthday));
		if(deadday!=null && deadday.length()>0)
			person.setDeadday(ValueUtils.toInteger(deadday));
		if(fatherId!=null && fatherId.length()>0)
			person.setFather(new PersonFilter(ValueUtils.toLong(fatherId)));
		if(motherId!=null && motherId.length()>0)
			person.setMother(new PersonFilter(ValueUtils.toLong(motherId)));
		if(familyId!=null&& familyId.length()>0)
			person.setFamily(new FamilyFilter(ValueUtils.toLong(familyId)));
		return person;
	}
}
