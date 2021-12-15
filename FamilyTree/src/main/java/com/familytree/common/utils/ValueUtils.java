package com.familytree.common.utils;

import com.familytree.common.enums.Sex;

public class ValueUtils {
	public static Long toLong(String value) {
		try {
			return Long.parseLong(value);
		}catch(Exception e) {
			return null;
		}
	}
	public static Integer toInteger(String value) {
		try {
			return Integer.parseInt(value);
		}catch(Exception e) {
			return null;
		}
	}
	public static Sex toSex(String value) {
		try {
			value = value.toUpperCase();
			return Sex.valueOf(value);
		}catch(Exception e) {
			return null;
		}
	}
	public static Boolean toBoolean(String value) {
		try {
			return Boolean.parseBoolean(value);
		}catch(Exception e) {
			return null;
		}
	}
}
