package com.familytree.filter;


import com.familytree.common.filter.Filter;

import lombok.Getter;
import lombok.Setter;
@Setter@Getter
public class MarryFilter extends Filter {
	private PersonFilter husband;
	private PersonFilter wife;
	private Integer husbandFlag;
	private Integer wifeFlag;
	private Integer marryDate;
	private Integer divorceDate;
	private Boolean divorced;
}
