package com.vvt.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
public class HtmlTag {
	public static final String[] SINGLETON_TAG = {
			"area", "base","br","col","command","embed",
			"hr","img","input","keygen","link","meta",
			"param","source","track","wbr"};
	// <input>
	private String tagName;
	private HashMap<String, Object> attrs = new HashMap<>();
	private List<Object> contents = new ArrayList<>();
	/**
	 * example 
	 * @param tagName : input, div, p....
	 */
	public HtmlTag(String tagName) {
		this.tagName = tagName;
	}
	/**
	 * null tag
	 */
	public HtmlTag() {
	}
	
	/**
	 * get tagName,
	 * example <input>, return: input
	 * @return
	 */
	public String getName() {
		return tagName;
	}
	/**
	 * Set tagName
	 * @param tagName
	 * @return this class
	 */
	public HtmlTag name(String tagName) {
		this.tagName = tagName;
		return this;
	}
	/**
	 * get all attributes of tag
	 * @return 
	 */
	public HashMap<String, Object> getAttrs() {
		return attrs;
	}
	/**
	 * Append a tag 
	 * @param childrenTag
	 * @return this tag
	 */
	public HtmlTag append(HtmlTag childrenTag) {
		this.contents.add(childrenTag);
		return this;
	}
	/**
	 * Append text into tag
	 * @param text
	 * @return this tag
	 */
	public HtmlTag append(String text) {
		contents.add(text);
		return this;
	}
	/**
	 * clear content of tag
	 * @return this tag
	 */
	public HtmlTag clearContent() {
		this.contents.clear();
		return this;
	}
	/**
	 * Get all contents of tag
	 * @return
	 */
	public List<Object> getContents() {
		return contents;
	}
	/**
	 * set attribute of tag
	 * @param attrName : attribute name
	 * @param attrValue : attribute value
	 * @return
	 */
	public HtmlTag attr(String attrName, Object attrValue) {
		attrs.put(attrName, attrValue);
		return this;
	}
	/**
	 * set attribute of tag
	 * Example: required
	 * @param attrName : attribute name
	 * @return
	 */
	public HtmlTag attr(String attrName) {
		attrs.put(attrName, null);
		return this;
	}
	/**
	 * set tag's class
	 * @param className
	 * @return
	 */
	public HtmlTag className(String className) {
		return attr("class", className);
	}
	/**
	 * Set tag's id
	 * @param id
	 * @return
	 */
	public HtmlTag id(String id) {
		return attr("id", id);
	}
	/**
	 * build to String
	 * @return
	 */
	public String build() {
		StringBuilder sb =new StringBuilder();
		appendTo(sb);
		return sb.toString();
	}
	/**
	 * append this tag into parentTag
	 * @param parentTag
	 */
	public void appendTo(HtmlTag parentTag) {
		parentTag.append(this);
	}
	/**
	 * append this tag into StringBuilder
	 * @param sb
	 */
	public void appendTo(StringBuilder sb) {
		if(tagName!=null && tagName.length()>0) {
			sb.append('<').append(tagName);
			// attribute
			attrs.forEach((key, val)->{
				sb.append(' ').append(key);
				if(val!=null)
					sb.append("=").append('"').append(val).append('"');
			});
			sb.append('>').append('\n');
		}

		//children tag
		contents.forEach(tag->{
			if(tag instanceof HtmlTag)
				((HtmlTag)tag).appendTo(sb);
			else
				sb.append(tag);
		});
			
		//end tag
		if(tagName!=null && tagName.length()>0 && !isSingletonTag())
			sb.append("</").append(tagName).append('>').append('\n');
	}
	/**
	 * this tag is singleton tag ?
	 * @return
	 */
	public boolean isSingletonTag() {
		if(this.tagName==null|| tagName.length()==0)
			return false;
		int start=0;
		int end =SINGLETON_TAG.length-1;
		while(end>=start) {
			int mid = (start+end)/2;
			int compa = this.tagName.compareTo(SINGLETON_TAG[mid]);
			if(compa==0)
				return true;
			else if(compa>0) {
				start= mid+1;
			}else {
				end= mid-1;
			}
		}
		return false;
	}
}





