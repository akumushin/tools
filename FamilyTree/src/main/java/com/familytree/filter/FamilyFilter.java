package com.familytree.filter;

import com.familytree.common.filter.Filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter@Getter
@NoArgsConstructor
public class FamilyFilter extends Filter {
	private Long id;
	private String place;
	private String name;
	private PersonFilter member;
	public FamilyFilter(Long id) {
		this.id =id;
	}
}
