
package com.example.models.responses;

public class JwtResponse {
    private final String jwt;
    private String username;
    public JwtResponse(String jwt,String username) {
        this.jwt = jwt;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }
    public String getUsername(){
        return username;
    }
}
