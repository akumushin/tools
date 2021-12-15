package com.familytree.filter;

import com.familytree.common.enums.Sex;
import com.familytree.common.filter.Filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter@Getter
@NoArgsConstructor
@ToString
public class PersonFilter extends Filter {
	private Long id;
	private String name;
	private Sex sex;
	private Boolean alive;
	private Integer birthday;
	private Integer deadday;
	private PersonFilter father;
	private PersonFilter mother;
	private FamilyFilter family;
	
	public PersonFilter(Long id) {
		this.id=id;
	}
}
