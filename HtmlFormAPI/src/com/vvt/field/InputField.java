package com.vvt.field;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 
 * @author Vũ Văn Thưởng
 *
 */
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface InputField {
	FieldTypeEnum type() default FieldTypeEnum.text;
	String label() default "";
	/**
	 * other attributes
	 * sample {"name='abc'","required", class="'zzz'"}
	 * @return
	 */
	String[] attrs() default {};
	/**
	 * attribute of <input>
	 * @return
	 */
	boolean required() default false;
	/**
	 * value of <option> tags 
	 * @return
	 */
	Choise[] choises() default {}; 
	
	String [] groups() default {"default"};
	
}
