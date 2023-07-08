package com.cognizant.moviebooking.model;

public class JwtTokenHolder {
    private static String jwtToken;

    public static void setJwtToken(String token) {
        jwtToken = token;
    }

    public static String getJwtToken() {
        return jwtToken;
    }
}

