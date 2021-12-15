package com.vvt.field;
import java.lang.annotation.Annotation;


import com.vvt.field.*;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
@SuppressWarnings("all")
public class SimpleChoise implements Choise {
	
	public SimpleChoise(String value, String label) {
		this.label =label;
		this.value = value;
		
	}
	private String label;
	private String value;
	@Override
	public Class<? extends Annotation> annotationType() {
		return this.getClass();
	}

	@Override
	public String label() {
		// TODO Auto-generated method stub
		return label;
	}

	@Override
	public String value() {
		// TODO Auto-generated method stub
		return value;
	}

}
