package com.program.weather.service.dto.property;

public class UserRestDTO {
    private long id;
    private String role;

    public void setId(long id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }
}
