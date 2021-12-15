package com.vvt.html;

import java.util.List;

import com.vvt.field.FieldTypeEnum;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
public class HtmlField extends HtmlTag{
	protected FieldTypeEnum type = FieldTypeEnum.none;
	public FieldTypeEnum getType() {
		return type;
	}
	public void setType(FieldTypeEnum type) {
		this.type = type;
	}
	public HtmlTag getInputTag() {
		return inputTag;
	}
	public void setInputTag(HtmlTag inputTag) {
		this.inputTag = inputTag;
	}
	public HtmlTag getLabelTag() {
		return labelTag;
	}
	public void setLabelTag(HtmlTag labelTag) {
		this.labelTag = labelTag;
	}
	
	public ErrorListTag getErrorListTag() {
		return errorListTag;
	}
	public void setErrorListTag(ErrorListTag errorListTag) {
		this.errorListTag = errorListTag;
	}

	protected HtmlTag inputTag;
	protected HtmlTag labelTag;
	protected String name;
	protected Object value;
	protected ErrorListTag errorListTag;
	public Object getValue() {
		return value;
	}
	public String getName() {
		return name;
	}
	/**
	 * set tag name is div, label first
	 * @return
	 */
	public HtmlField asDefault() {
		this.name("div");
		if(type==FieldTypeEnum.radio|| type==FieldTypeEnum.checkbox)
			return labelLast();
		else
			return labelFirst();
	}
	/**
	 * set tag name is tr
	 * @return
	 */
	public HtmlField asTable() {
		HtmlTag td1 = new HtmlTag("td").append(labelTag);
		HtmlTag td2 = new HtmlTag("td").append(inputTag);
		if(errorListTag!=null)
			td2.append(errorListTag);
		this.clearContent().append(td1).append(td2).name("tr");
		return this;
	}
	public HtmlField labelFirst() {
		this.clearContent().append(labelTag).append(inputTag);
		if(errorListTag!=null)
			this.append(errorListTag);
		return this;
	}

	public HtmlField labelLast() {
		this.clearContent().append(inputTag).append(labelTag);
		if(errorListTag!=null)
			this.append(errorListTag);
		return this;
	}
	/**
	 * 
	 * @param name
	 * @param value
	 * @param label
	 * @param errorList
	 */
	public HtmlField(String name, Object value, String label, List<String> errorList) {
		this.name("div");
		this.name = name;
		this.value = value;
		if(errorList!=null)
			this.errorListTag = new ErrorListTag(errorList);
		// *******set label*******
		labelTag = new HtmlTag();// not set
		if (label != null && label.length() > 0)
			labelTag.name("label").attr("for", name).append(label);

		// ********initial input tag*********
		inputTag = new HtmlTag().attr("name", name).attr("value", value).id(name); // not set tag name
		
		//	**********default setting*********
		this.asDefault();
	}
	/**
	 * 
	 * @param name
	 * @param value
	 * @param label
	 */
	public HtmlField(String name, Object value, String label) {
		this(name, value, label, null);
	}
}