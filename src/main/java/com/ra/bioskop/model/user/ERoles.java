package com.ra.bioskop.model.user;


public enum ERoles {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_CUSTOMER("ROLE_CUSTOMER");

    private String name;

    ERoles() {}
    ERoles(String name) {
        this.name = name;
    }

    public static ERoles getRole(String role) {
        if ("admin".equals(role.toLowerCase().trim())) {
            return ERoles.ROLE_ADMIN;
        }
        return ERoles.ROLE_CUSTOMER;
    }
}
