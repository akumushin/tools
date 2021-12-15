package com.vvt.field;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/**
 * 
 * @author Vũ Văn Thưởng
 * @version 1.0
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface Choise {
	/**
	 * display name
	 * @return
	 */
	String label() default "";
	/**
	 * value
	 * @return
	 */
	String value() default "";
}
