package com.vvt.html;

import java.util.List;

public class ErrorListTag extends HtmlTag{
	public ErrorListTag(List<String> list) {
		super("ul");
		for(String item:list) {
			HtmlTag tag= new HtmlTag("li");
			tag.append(item).appendTo(this);
		}
		
	}
}
