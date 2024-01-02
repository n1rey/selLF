package com.portfolio.sellf.global.common.firebase.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.google.firebase.messaging.Notification.Builder;
import com.portfolio.sellf.global.common.firebase.vo.FcmMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMService {
  private final FirebaseMessaging firebaseMessaging;

  public String sendNotificationByToken(FcmMessage fcmVo) {
    Notification notification = Notification.builder()
    .setTitle("제목")
    .setBody("내용")
    .build();

    Message message = Message.builder()
    .setNotification(notification)
    .build();

    try {
      firebaseMessaging.send(message);
      return "알림을 성공적으로 전송했습니다.";
    } catch (Exception e) {
      e.printStackTrace();
      return "알림 보내기를 실패했습니다.";
    }
  }
  private String getAccessToken() throws IOException {
    // firebase로 부터 access token을 가져온다.
    GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new ClassPathResource("firebase/serviceAccountKey.json").getInputStream())
            .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
    googleCredentials.refreshIfExpired();
    return googleCredentials.getAccessToken().getTokenValue();
  }
}
