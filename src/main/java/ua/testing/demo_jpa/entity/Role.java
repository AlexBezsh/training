package ua.testing.demo_jpa.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    SUPERADMIN,
    ADMIN,
    USER,
    USER_WEB;

    @Override
    public String getAuthority() {
        return name();
    }
}
