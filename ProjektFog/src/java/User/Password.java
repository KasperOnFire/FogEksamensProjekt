package User;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * This class contains everything related to hashing passwords for security.
 *
 * @author Kasper
 */
public class Password {

    private String passwordSalt;

    /**
     * Generates a salt for the hashing
     *
     * @return the salt String
     */
    public String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        passwordSalt = saltStr;
        return saltStr;
    }

    /**
     *
     * @param passwordToHash the original password to hashed. Can be in plaintext, but can also be pre-hashed in JavaScript
     * @param salt The salt string to add to the password
     * @return the hashed password as a (long) String
     * @throws UnsupportedEncodingException if the hashing somehow fails
     */
    public String get_SHA_512_SecurePassword(String passwordToHash, String salt) throws UnsupportedEncodingException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("ERROR Password 1:");
            e.printStackTrace();
        }
        return generatedPassword;
    }

    /**
     * The salt String
     * @return the Salt
     */
    public String getPasswordSalt() {
        return passwordSalt;
    }
}
