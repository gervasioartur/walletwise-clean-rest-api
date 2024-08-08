package com.br.walletwise.core.validation.validators;

import com.br.walletwise.core.validation.AbstractValidator;

public class UsernameValidator extends AbstractValidator {
    private final String returnMessage;

    public UsernameValidator(Object fieldValue) {
        this.fieldValue = fieldValue;
        this.returnMessage = "Username is invalid.";
    }

    @Override
    public String validate() {
        String username = (String) fieldValue;
        if (username.contains("@"))
            return returnMessage;
        return null;
    }
}