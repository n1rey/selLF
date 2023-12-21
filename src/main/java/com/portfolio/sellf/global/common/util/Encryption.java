package com.portfolio.sellf.global.common.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Component;

@Component
public class Encryption {
  public static final String private_key = "2023selLFAesKeyCode1206leng32byt";

  public static String encodeSha(String planeText) {
    String encodingText = "";
    try {
      // SHA-512, SHA-256, SHA1 등 다양한 방식으로 활용
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(planeText.getBytes(StandardCharsets.UTF_8));
      encodingText = DatatypeConverter.printBase64Binary(md.digest());
    } catch (NoSuchAlgorithmException e) {
      // throws가 싫어서 RuntimeException을 사용
      throw new RuntimeException(e);
    }
    //AES대칭키 암호화로 한번더 암호화
    return encodeAes(encodingText);
  }
  
  public static String encodeAes(String planeText) {
    String encodingString = "";

    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(private_key.getBytes("UTF-8"), "AES");
      IvParameterSpec ivParamSpec = new IvParameterSpec(private_key.substring(0, 16).getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

      byte[] encrypted = cipher.doFinal(planeText.getBytes("UTF-8"));
      encodingString = Base64.getEncoder().encodeToString(encrypted);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return encodingString;
  }

  public static String decodeAes(String encodeText) {
    String encodingString = "";

    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
      SecretKeySpec keySpec = new SecretKeySpec(private_key.getBytes("UTF-8"), "AES");
      IvParameterSpec ivParamSpec = new IvParameterSpec(private_key.substring(0, 16).getBytes());
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

      byte[] decodedBytes = Base64.getDecoder().decode(encodeText);
      byte[] decrypted = cipher.doFinal(decodedBytes);
      encodingString = new String(decrypted, "UTF-8");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return encodingString;
  }
}
