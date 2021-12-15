package com.familytree.common.utils;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodArgumentBuilder;

import com.familytree.common.enums.Sex;

public class Convert {
	public String date(int value, String formatString) {
		int year =value/10000;
		value= value %10000;
		int month = value/100;
		int day= value %100;
		return formatString
				.replace("YYYY", ((year==0)?"####":String.valueOf(year)))
				.replace("MM", ((month==0)?"##":String.valueOf(month)))
				.replace("DD", ((day==0)?"##":String.valueOf(day)));
	}
	
	public String sex(Sex sex, String male, String female, String unknown) {
		switch (sex) {
		case MALE :
			return male;
		case FEMALE:
			return female;
		default:
			return unknown;
		}
	}
	public String sex(String sex, String male, String female, String unknown, String defaults) {
		switch (sex) {
		case "MALE" :
			return male;
		case "FEMALE":
			return female;
		case "UNKNOWN":
			return unknown;
		default:
			return defaults;
		}
	}
	public String url(String name, Object ...args) {
		MethodArgumentBuilder builder= MvcUriComponentsBuilder.fromMappingName(name);
		for(int i=0;i<args.length;i++)
			builder.arg(i, args[i]);
		return builder.build();
	}
	
	public String divorced(boolean value, String trueResult, String falseResult) {
		if(value)
			return trueResult;
		return falseResult;
	}
}
