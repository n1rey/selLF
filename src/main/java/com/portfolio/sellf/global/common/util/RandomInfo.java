package com.portfolio.sellf.global.common.util;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomInfo {
  
  public static String randomId(int length) {
    String id = "";
    final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_+-=[]{}|/";
    StringBuilder sb = new StringBuilder(8);
    Random random = new SecureRandom();
    for (int i = 0; i < length; i++) {
        int randomIndex = random.nextInt(CHARACTERS.length());
        char randomChar = CHARACTERS.charAt(randomIndex);
        sb.append(randomChar);
    }
    id = sb.toString();
    return id;
  }


  public static String randomPassword() {
    String password = "";
    char[] charSet = new char[] {
      '0', '1', '2', '3', '4', '5', '6',
      '7', '8', '9', 'A', 'B', 'C', 'D',
      'E', 'F', 'G', 'H', 'I', 'J', 'K', 
      'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
      'S', 'T', 'U', 'V', 'W', 'X', 'Y', 
      'Z', 'a', 'b', 'c', 'd', 'e', 'f', 
      'g', 'h', 'i', 'j', 'k', 'l', 'm', 
      'n', 'o', 'p', 'q', 'r', 's', 't', 
      'u', 'v', 'w', 'x', 'y', 'z', '!', 
      '@', '#', '$', '%', '^', '&' };
        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());
        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<10; i++) {
          idx = sr.nextInt(len);
          sb.append(charSet[idx]);
        }
        password =  sb.toString();
    return password;
  }
}
