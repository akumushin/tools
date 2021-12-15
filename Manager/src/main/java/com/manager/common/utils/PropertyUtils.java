package com.manager.common.utils;

public class PropertyUtils {
	public static Object getProperty(Object obj, String propertyName) {
		try {
			var field = obj.getClass().getDeclaredField(propertyName);
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			return null;
		}
	}
	public static boolean hasProperty(Object obj, String propertyName) {
		try {
			obj.getClass().getDeclaredField(propertyName);
			return false;
		} catch (NoSuchFieldException | SecurityException e) {
			return true;
		}
	}
}
