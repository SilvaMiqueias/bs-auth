package com.example.auth.security.utils;

public @interface ValidationsLogin {
    public static final String[] ENDPOINTS_WITH_AUTHENTICATION_NOT_REQUIRED = {
            "/auth/users/login",
            "/auth/users",
            "/auth/users/create",
            "/cst/public/**",
            "/report/public/**"

    };

    // Endpoints que requerem autenticação para serem acessados
    String [] ENDPOINTS_WITH_AUTHENTICATION_REQUIRED = {
            "/auth/users/get-user",
            "/auth/users/update",
            "/auth/users/update-password",
            "/auth/test/**",
            "/report/auth/**"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de cliente
    public static final String [] ENDPOINTS_CUSTOMER = {
            "/auth/users/test/customer"
    };

    // Endpoints que só podem ser acessador por usuários com permissão de administrador
    public static final String [] ENDPOINTS_ADMIN = {
            "/auth/users/test/administrator"
    };
}
