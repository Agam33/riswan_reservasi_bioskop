package com.ra.bioskop.model.user;


public enum ERoles {
    ROLE_ADMIN("admin"),
    ROLE_CUSTOMER("customer");

    private String name;

    ERoles() {}
    ERoles(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
