package com.manager.common.utils;

import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.MethodArgumentBuilder;

public class Convert {
	public static String url(String name, Object ...args) {
		MethodArgumentBuilder builder= MvcUriComponentsBuilder.fromMappingName(name);
		for(int i=0;i<args.length;i++)
			builder.arg(i, args[i]);
		return builder.build();
	}
}
