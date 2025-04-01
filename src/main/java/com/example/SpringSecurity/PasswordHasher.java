package com.example.SpringSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHasher {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pt = "admin@1234";
        String hashedPassword = encoder.encode(pt);
        System.out.println(hashedPassword);
    }
}
