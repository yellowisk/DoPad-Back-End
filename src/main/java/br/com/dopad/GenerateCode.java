package br.com.dopad;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class GenerateCode {
    public static void main(String args[]) throws NoSuchAlgorithmException {
                String inputString = "Yellow Page" + "Blueisk" + System.currentTimeMillis();
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hashBytes = digest.digest(inputString.getBytes(StandardCharsets.UTF_8));
                String encodedHash = Base64.getEncoder().encodeToString(hashBytes);
                System.out.println(encodedHash);
        }
}
