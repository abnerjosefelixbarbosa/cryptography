package com.backend.java.backendjava.infra.services;

import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class EncryptionService {

    public String encript(String value) {
        try {
            var messageDigest = MessageDigest.getInstance("SHA-512");
            var bytes = messageDigest.digest(value.getBytes());
            var stringBuilder = new StringBuilder();
            for (byte b : bytes) {
                stringBuilder.append(String.format("%02x", b));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean compareEncript(String value, String hash) {
        var valueHash = encript(value);
        return hash.equals(valueHash);
    }
}
