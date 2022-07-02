package com.example.ddmopenevents2.business;

import java.io.Serializable;

public class BearerToken implements Serializable {
    private String accessToken;

    public BearerToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public BearerToken() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
