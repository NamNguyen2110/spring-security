package com.practice.spring.security.common.validator.factory;


import com.practice.spring.security.common.validator.ValidatorService;
import com.practice.spring.security.common.validator.group.*;

public class ValidatorFactory {

    public static ValidatorService getInstant(BaseGroup baseGroup) {
        if (baseGroup instanceof MinLengthGroup)
            return new MinLengthService();
        if (baseGroup instanceof MaxLengthGroup)
            return new MaxLengthService();
        if (baseGroup instanceof RegexGroup)
            return new RegexService();
        if (baseGroup instanceof RequiredGroup)
            return new RequiredService();
        if (baseGroup instanceof MinValueGroup)
            return new MinValueService();
        if (baseGroup instanceof MaxValueGroup)
            return new MaxValueService();
        return null;
    }

}
