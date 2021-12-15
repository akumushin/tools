package com.manager.common.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.manager.common.utils.PropertyUtils;

import lombok.Data;
@Data
public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
	private String message;
	private String firstFieldName;
	private String secondFieldName;
	
	@Override
    public void initialize(final FieldMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.matchField();
        secondFieldName = constraintAnnotation.field();
        message = constraintAnnotation.message();
    }
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Object firstField = PropertyUtils.getProperty(value, firstFieldName);
		Object secondField = PropertyUtils.getProperty(value, secondFieldName);
		if(firstField == null && secondField == null || firstField !=null && firstField.equals(secondField))
			return true;
		
		context
			.buildConstraintViolationWithTemplate(message)
			.addPropertyNode(secondFieldName)
			.addConstraintViolation()
			.disableDefaultConstraintViolation();
		return false;
	}

}
