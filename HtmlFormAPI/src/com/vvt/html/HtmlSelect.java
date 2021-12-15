package com.vvt.html;

import java.util.List;
import java.util.Objects;

import com.vvt.field.Choise;
import com.vvt.field.FieldTypeEnum;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
public class HtmlSelect extends HtmlField{
	/**
	 * Select tag constructor
	 * @param name
	 * @param value
	 * @param label
	 * @param choises
	 * @param attrs
	 * @param errorList
	 */
	public HtmlSelect(String name, Object value, String label, Choise[] choises,String[] attrs,List<String> errorList) {
		super(name, value, label, errorList);
		this.type = FieldTypeEnum.select;
		inputTag.name("select");
		if(attrs!=null) {
			for(String attr: attrs) {
				inputTag.attr(attr);
			}
		}
		for(Choise choise: choises) {
			HtmlTag choiseTag = new HtmlTag("option").attr("value", choise.value()).append(choise.label());
			if(Objects.equals(value, choise.value()))
				choiseTag.attr("selected");
			inputTag.append(choiseTag);
		}
	}
	/**
	 * Select tag constructor
	 * @param name
	 * @param value
	 * @param label
	 * @param choises
	 */
	public HtmlSelect(String name, Object value, String label, Choise[] choises) {
		this(name, value, label, choises, null,null);
	}
	/**
	 * Select tag constructor
	 * @param name
	 * @param value
	 * @param label
	 * @param choises
	 * @param attrs
	 */
	public HtmlSelect(String name, Object value, String label, Choise[] choises, String[] attrs) {
		this(name, value, label, choises, attrs,null);
	}
}
