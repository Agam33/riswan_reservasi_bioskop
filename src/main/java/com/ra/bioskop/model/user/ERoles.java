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
        switch (role.toLowerCase().trim()) {
            case "admin":
                return ERoles.ROLE_ADMIN;
            default:
                return ERoles.ROLE_CUSTOMER;
        }
    }
}
