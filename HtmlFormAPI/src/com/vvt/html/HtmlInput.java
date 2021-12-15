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
public class HtmlInput extends HtmlField {
	/**
	 * input tag constructor
	 * @param name
	 * @param value
	 * @param label
	 * @param type
	 * @param required
	 * @param attrs
	 * @param choises
	 * @param errorList
	 */
	public HtmlInput(String name, Object value, String label, FieldTypeEnum type, boolean required, String[] attrs,
			Choise[] choises, List<String> errorList) {
		super(name, value, label,errorList);
		// options is blank
		if(choises ==null || choises.length == 0) {
			this.type = type;
			inputTag.name("input").attr("type", type.name());

			if (required)
				inputTag.attr("required");
			if (attrs != null) 
				for (String attr : attrs) {
					inputTag.attr(attr);
				}
			return;
		}
		// options is not blank
		// multiple options
		int index=1;
		for (Choise choise : choises) { //
			HtmlInput choiseTag = new HtmlInput(name, choise.value(), choise.label(), type, required, attrs);
			String choiseId=name+"-"+ index++;
			choiseTag.labelTag.attr("for",choiseId);
			choiseTag.inputTag.id(choiseId);
			if(type ==FieldTypeEnum.checkbox|| type ==FieldTypeEnum.radio) {
				if(Objects.equals(choise.value() , value))
					choiseTag.inputTag.attr("checked");
			}
			inputTag.append(choiseTag);
		}
	}

	/**
	 * 
	 * @param name
	 * @param value
	 * @param label
	 * @param type
	 * @param required
	 * @param attrs
	 */
	public HtmlInput(String name, Object value, String label, FieldTypeEnum type, boolean required, String[] attrs) {
		this(name, value, label, type, required, attrs, null, null);
	}
	/**
	 * input tag constructor
	 * @param name
	 * @param value
	 * @param label
	 * @param type
	 * @param required
	 * @param attrs
	 * @param choises
	 */
	public HtmlInput(String name, Object value, String label, FieldTypeEnum type, boolean required, String[] attrs, Choise[] choises) {
		this(name, value, label, type, required, attrs, choises, null);
	}
	
}
