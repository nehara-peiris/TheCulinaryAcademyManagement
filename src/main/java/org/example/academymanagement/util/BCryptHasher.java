package org.example.academymanagement.util;


import org.mindrot.jbcrypt.BCrypt;

public class BCryptHasher {

    /**
     * Hashes a plaintext password.
     *
     * @param plainTextPassword the plaintext password to hash
     * @return the hashed password
     */
    public static String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    /**
     * Verifies a plaintext password against a hashed password.
     *
     * @param plainTextPassword the plaintext password to verify
     * @param hashedPassword    the hashed password to compare against
     * @return true if the passwords match, false otherwise
     */
    public static boolean verifyPassword(String plainTextPassword, String hashedPassword) {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
