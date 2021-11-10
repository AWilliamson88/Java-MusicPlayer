package musicplayer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author P113357
 */
public class SongHash {
    

    public String getHash(String songTitle) {
        String songHash = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(getSalt());
            byte[] bytes = md.digest(songTitle.getBytes());
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            songHash = sb.toString();
            
        } catch(Exception e) {
            songHash = "Unable to hash song title.";
        }
        
        return songHash;
    }
    
    private static byte[] getSalt() throws NoSuchAlgorithmException {

        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        return salt;
    }
    
}
