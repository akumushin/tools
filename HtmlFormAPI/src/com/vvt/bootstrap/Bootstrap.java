package com.vvt.bootstrap;

import com.vvt.html.HtmlField;
import com.vvt.html.HtmlForm;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
public final class Bootstrap {
	/**
	 * convert form to html text
	 * @param form
	 * @return
	 */
	public static String asDiv(Object form) {
		HtmlForm htmlForm = new HtmlForm(form);
		return asDiv(htmlForm);
	}
	/**
	 * convert form to html text
	 * @param form
	 * @param fieldErrors// have method errors(fieldName) return String[]
	 * @return
	 */
	public static String asDiv(Object form, Object fieldErrors) {
		HtmlForm htmlForm = new HtmlForm(form, fieldErrors);
		return asDiv(htmlForm);
	}
	/**
	 * convert htmlForm to html text
	 * @param htmlForm
	 * @return
	 */
	public static String asDiv(HtmlForm htmlForm) {
		StringBuilder sb= new StringBuilder();
		htmlForm.getHtmlFields().forEach(field->{
			setClassOfField(field);
			field.appendTo(sb);
		});
		return sb.toString();
	}
	private static void setClassOfField(HtmlField field) {
		if(field.getErrorListTag()!=null)
			field.getErrorListTag().className("text-danger");
		switch (field.getType()) {
		case none:
			field.className("form-group");
			field.getInputTag().getContents().forEach(item->{
				HtmlField subInput = (HtmlField) item;
				setClassOfField(subInput);
			});
			break;
		case select:
			field.className("form-group");
			field.getInputTag().className("form-control");
			break;
		case checkbox:
		case radio:
			field.className("form-check");
			field.labelLast();
			field.getInputTag().className("form-check-input");
			field.getLabelTag().className("form-check-label");
			break;
		default:
			field.className("form-group");
			field.getInputTag().className("form-control");
			break;
		}
	}
	
	public static String asTable(Object form) {
		HtmlForm htmlForm = new HtmlForm(form);
		return asTable(htmlForm);
	}
	public static String asTable(Object form, Object errorFields) {
		HtmlForm htmlForm = new HtmlForm(form, errorFields);
		return asTable(htmlForm);
	}
	public static String asTable(HtmlForm htmlForm) {
		StringBuilder sb= new StringBuilder();
		htmlForm.getHtmlFields().forEach(field->{
			field.asTable();
			setClassOfField(field);
			field.appendTo(sb);
		});
		return sb.toString();
	}
	public static String asNone(HtmlForm htmlForm) {
		StringBuilder sb= new StringBuilder();
		htmlForm.getHtmlFields().forEach(field->{
			field.name("");
			field.appendTo(sb);
		});
		return sb.toString();
	}
	public static String asNone(Object form) {
		return asNone(new HtmlForm(form));
	}
	public static String asNone(Object form, Object fieldErrors) {
		return asNone(new HtmlForm(form, fieldErrors));
	}
}
