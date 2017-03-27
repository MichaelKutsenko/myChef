package com.myChef;

import com.myChef.security.Authority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Mocart
 */
public class Pasword {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update("user".getBytes());
        byte[] digest = md.digest();
        System.out.println(new String(Hex.encode(digest)));

        System.out.println(new BCryptPasswordEncoder().encode("user"));
    }
}
