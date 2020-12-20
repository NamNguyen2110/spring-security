package com.practice.spring.security.common.validator.factory;


import com.practice.spring.security.common.utils.CommonUtils;
import com.practice.spring.security.common.validator.ValidateObject;
import com.practice.spring.security.common.validator.ValidatorMessage;
import com.practice.spring.security.common.validator.ValidatorService;
import com.practice.spring.security.common.validator.exception.FormatInvalidException;
import com.practice.spring.security.common.validator.group.MessageField;
import com.practice.spring.security.common.validator.group.RequiredGroup;

public class RequiredService implements ValidatorService {

    @Override
    public ValidatorMessage process(ValidateObject object) throws FormatInvalidException {
        return required(object.getValue(), object.getFieldName(), (RequiredGroup) object.getValidatorGroup());
    }

    public ValidatorMessage required(Object value, String fieldName, RequiredGroup requiredGroup) throws FormatInvalidException {
        if (requiredGroup.isRequired() && isEmpty(value)) {
            return new ValidatorMessage(MessageField.required, fieldName);
        }
        return null;
    }

    private boolean isEmpty(Object value) {
        if (value == null)
            return true;
        if (value instanceof String)
            return CommonUtils.isNullOrEmpty((String) value);

        return false;
    }
}
