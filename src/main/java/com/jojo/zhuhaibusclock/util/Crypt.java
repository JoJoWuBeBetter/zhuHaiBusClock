package com.jojo.zhuhaibusclock.util;

import org.apache.commons.text.StringEscapeUtils;
import retrofit2.http.PUT;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author JoJoWu
 */
public class Crypt {
    static final String BUS_KEY = "Dsas1a3e7r@#4x!d";


    public static String decrypt(String encryptedData, String iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(BUS_KEY.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());

        Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedDataBytes = decoder.decode(encryptedData);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] decryptedDataBytes = cipher.doFinal(encryptedDataBytes);
        return StringEscapeUtils.unescapeJava(new String(decryptedDataBytes));
    }

    public static String wxDecrypt(String sessionKey, String encryptedData, String iv) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] sessionKeyBytes = decoder.decode(sessionKey);
        byte[] encryptedDataBytes = decoder.decode(encryptedData);
        byte[] ivBytes = decoder.decode(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(sessionKeyBytes, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);
        byte[] decryptedDataBytes = cipher.doFinal(encryptedDataBytes);
        return StringEscapeUtils.unescapeJava(new String(decryptedDataBytes));
    }
}
