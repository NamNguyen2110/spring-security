package com.practice.spring.security.common.validator.factory;


import com.practice.spring.security.common.utils.CommonUtils;
import com.practice.spring.security.common.validator.ValidateObject;
import com.practice.spring.security.common.validator.ValidatorMessage;
import com.practice.spring.security.common.validator.ValidatorService;
import com.practice.spring.security.common.validator.exception.FormatInvalidException;
import com.practice.spring.security.common.validator.group.MaxLengthGroup;
import com.practice.spring.security.common.validator.group.MessageField;
import com.practice.spring.security.common.validator.group.MinLengthGroup;

import java.math.BigDecimal;

public class MinLengthService implements ValidatorService {

    @Override
    public ValidatorMessage process(ValidateObject object)  throws FormatInvalidException {
        return length(object.getValue(), object.getFieldName(), (MinLengthGroup)object.getValidatorGroup());
    }

    public static ValidatorMessage length(Object value, String fieldName, MinLengthGroup group)  throws FormatInvalidException{

        if(value instanceof String) {
            String value1 = (String)value;
            if (!CommonUtils.isEmptyObject(value) && value1.length() < group.getMin()) {
                    return new ValidatorMessage(MessageField.minLength, fieldName, group.getMin());
                }
        }

        if(value instanceof Long) {
            Long value1 = (Long)value;
            if (!CommonUtils.isEmptyObject(value) && BigDecimal.valueOf(value1).setScale(0).toPlainString().length() < group.getMin()) {
                    return new ValidatorMessage(MessageField.minLength, fieldName);
                }
        }

        if(value instanceof Integer) {
            Integer value1 = (Integer)value;
            if (!CommonUtils.isEmptyObject(value) && BigDecimal.valueOf(value1).setScale(0).toPlainString().length() < group.getMin()) {
                    return new ValidatorMessage(MessageField.minLength, fieldName);
                }
        }


        if(value instanceof BigDecimal) {
            BigDecimal value1 = (BigDecimal)value;
            if (!CommonUtils.isEmptyObject(value) && value1.setScale(0).toPlainString().length() < group.getMin()) {
                    return new ValidatorMessage(MessageField.minLength, fieldName);
                }
        }

        return null;
    };
}
