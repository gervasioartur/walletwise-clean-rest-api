package com.br.walletwise.application.usecasesimpl.user;

import com.br.walletwise.application.gateway.user.GenerateTokenGateway;
import com.br.walletwise.usecase.user.GenerateToken;

public class GenerateTokenImpl implements GenerateToken {
    private final GenerateTokenGateway generateTokenGateway;

    public GenerateTokenImpl(GenerateTokenGateway generateTokenGateway) {
        this.generateTokenGateway = generateTokenGateway;
    }

    @Override
    public String generate(String username) {
        return this.generateTokenGateway.generate(username);
    }
}
