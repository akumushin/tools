package com.manager.common.utils;

import java.time.LocalDate;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class ValueUtils {
	public static Long toLongOrElse(String value, Long other) {
		try {
			return Long.parseLong(value);
		}
		catch (Exception e) {
			return other;
		}
	}
	public static Long toLong(String value) {
		return toLongOrElse(value, null);
	}
	
	public static PasswordEncoder pwdEncoder = new  BCryptPasswordEncoder();
	
	public static String encodePassword(String rawPassword) {
		return pwdEncoder.encode(rawPassword);
	}
	public static LocalDate toLocalDateOrElse(String value, LocalDate date) {
		try {
			return LocalDate.parse(value);
		}
		catch (Exception e) {
			return date;
		}
	}
	public static LocalDate toLocalDate(String value) {
		return toLocalDateOrElse(value, null);
	}
}
