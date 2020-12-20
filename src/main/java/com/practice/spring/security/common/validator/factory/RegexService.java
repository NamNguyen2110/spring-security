package com.practice.spring.security.common.validator.factory;


import com.practice.spring.security.common.utils.CommonUtils;
import com.practice.spring.security.common.validator.ValidateObject;
import com.practice.spring.security.common.validator.ValidatorMessage;
import com.practice.spring.security.common.validator.ValidatorService;
import com.practice.spring.security.common.validator.exception.FormatInvalidException;
import com.practice.spring.security.common.validator.group.MaxLengthGroup;
import com.practice.spring.security.common.validator.group.MessageField;
import com.practice.spring.security.common.validator.group.RegexGroup;

import java.util.regex.Pattern;

public class RegexService implements ValidatorService {

    @Override
    public ValidatorMessage process(ValidateObject object)  throws FormatInvalidException {
        if(object.getValue() instanceof String)
            return regex((String)object.getValue(), object.getFieldName(), (RegexGroup)object.getValidatorGroup());
        return new ValidatorMessage(MessageField.regex, object.getFieldName(),((RegexGroup)object.getValidatorGroup()).getRegex());
    }

    public ValidatorMessage regex(String value, String fieldName, RegexGroup regexGroup)  throws FormatInvalidException {
        if(!CommonUtils.isEmptyObject(value) && !validSpecialChar(regexGroup.getRegex(), value)) {
            return new ValidatorMessage(MessageField.regex, fieldName, regexGroup.getRegex());
        }
        return null;
    }

    private static boolean validSpecialChar(String patternString, String input){
        Pattern pattern = Pattern.compile(patternString);
        if(!pattern.matcher(input).matches()){
            return false;
        }
        return true;
    }
}
