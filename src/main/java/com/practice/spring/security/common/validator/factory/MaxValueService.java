package com.practice.spring.security.common.validator.factory;


import com.practice.spring.security.common.utils.CommonUtils;
import com.practice.spring.security.common.validator.ValidateObject;
import com.practice.spring.security.common.validator.ValidatorMessage;
import com.practice.spring.security.common.validator.ValidatorService;
import com.practice.spring.security.common.validator.exception.FormatInvalidException;
import com.practice.spring.security.common.validator.group.MaxValueGroup;
import com.practice.spring.security.common.validator.group.MessageField;

import java.math.BigDecimal;

public class MaxValueService implements ValidatorService {

    @Override
    public ValidatorMessage process(ValidateObject object)  throws FormatInvalidException {
        return length(object.getValue(), object.getFieldName(), (MaxValueGroup)object.getValidatorGroup());
    }

    public static ValidatorMessage length(Object value, String fieldName, MaxValueGroup group) throws FormatInvalidException{

        if(value instanceof String) {
            throw new FormatInvalidException(FormatInvalidException.FORMAT_INVALID);
        }

        if(value instanceof Long) {
            Long value1 = (Long)value;
            if (!CommonUtils.isEmptyObject(value) && value1 > group.getMaxValue()) {
                    return new ValidatorMessage(MessageField.maxValue, fieldName);
                }
        }

        if(value instanceof Integer) {
            Integer value1 = (Integer)value;
            if (!CommonUtils.isEmptyObject(value) &&  value1 > group.getMaxValue()) {
                return new ValidatorMessage(MessageField.maxValue, fieldName);
            }
        }


        if(value instanceof BigDecimal) {
            BigDecimal value1 = (BigDecimal)value;
            if (!CommonUtils.isEmptyObject(value) && value1.longValue() > group.getMaxValue()) {
                    return new ValidatorMessage(MessageField.maxValue, fieldName);
            }
        }

        if(value instanceof Double) {
            Double value1 = (Double)value;
            if (!CommonUtils.isEmptyObject(value) && value1.doubleValue() > group.getMaxValue()) {
                return new ValidatorMessage(MessageField.maxValue, fieldName);
            }
        }

        if(value instanceof Float) {
            Float value1 = (Float)value;
            if (!CommonUtils.isEmptyObject(value) && value1.floatValue() > group.getMaxValue()) {
                return new ValidatorMessage(MessageField.maxValue, fieldName);
            }
        }

        return null;
    };
}
