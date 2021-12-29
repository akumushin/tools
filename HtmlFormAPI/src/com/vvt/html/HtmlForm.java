package com.vvt.html;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.vvt.field.InputField;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
public class HtmlForm {
	private List<HtmlField> htmlFields;
	public List<HtmlField> getHtmlFields() {
		return htmlFields;
	}
	private Object form;
	private Object fieldErrors;
	
	public boolean hasError(String fieldName) {
		try {
			Method errorMethod = fieldErrors.getClass().getMethod("hasErrors", String.class);
			return (boolean) errorMethod.invoke(fieldErrors, fieldName);
		} catch (Exception e) {
			return true;
		} 
	}
	@SuppressWarnings("unchecked")
	public List<String> errors(String fieldName) {
		
		
		try {
			Method errorMethod = fieldErrors.getClass().getMethod("errors", String.class);
			return (List<String>) errorMethod.invoke(fieldErrors, fieldName);
		} catch (Exception e) {
			return new ArrayList<String>();
		} 
	}
	
	public HtmlForm(Object form) {
		this(form, null);
	}
	public HtmlForm(Object form, Object fieldErrors) {
		this.fieldErrors = fieldErrors;
		this.form = form;
		htmlFields = new ArrayList<HtmlField>() ;
		for(Field field : form.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			//	the field is input field
			try {
				InputField fieldType= field.getAnnotation(InputField.class);
				HtmlField htmlField=null;
				switch (fieldType.type()) {
				case select:
					htmlField =new HtmlSelect(
							field.getName(), //field name
							field.get(form), //field value
							fieldType.label(), //label
							fieldType.choises(),//append options
							fieldType.attrs());//more attributes
					break;
				default:
					htmlField=new HtmlInput(
							field.getName(), //field name
							field.get(form), //field value
							fieldType.label(), //label
							fieldType.type(), //input type
							fieldType.required(), //required attribute
							fieldType.attrs(), //more attributes
							fieldType.choises());// append children inputs
					break;
				}
				if(htmlField!=null) {
					//errors(field.getName()).forEach(System.out::println);
					htmlField.setErrorListTag(new ErrorListTag(errors(field.getName())));
					htmlField.asDefault();
					htmlFields.add(htmlField);
				}
			}catch(Exception e) {
				//System.out.println(field.getName());
				//e.printStackTrace();
			}
		}
	}
	
	/**
	 * return data form
	 * @return
	 */
	public Object getForm() {
		return form;
	}
	/**
	 * append a HtmlField into form
	 * @param fieldGroup
	 */
	public void append(HtmlField htmlField) {
		this.htmlFields.add(htmlField);
	};
}
