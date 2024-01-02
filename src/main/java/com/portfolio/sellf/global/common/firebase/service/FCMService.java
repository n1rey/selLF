package com.portfolio.sellf.global.common.firebase.service;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.portfolio.sellf.global.common.firebase.vo.FCMVo;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMService {
  private final FirebaseMessaging firebaseMessaging;
  private final String API_URL = "https://fcm.googleapis.com/v1/projects/sellf-e6068/messages:send";

  public String sendNotificationByToken(FCMVo fcmVo) {
    try {
      pushAlarm(fcmVo);
      return "알림을 성공적으로 전송했습니다.";
    } catch (Exception e) {
      e.printStackTrace();
      return "알림 보내기를 실패했습니다.";
    }
  }

  private void pushAlarm(FCMVo fcmVo) throws FirebaseMessagingException, IOException {
    Message message = getMessage(fcmVo);
    sendMessage(message);
  }

  private Message getMessage(FCMVo fcmVo) throws IOException {
    Notification notification = Notification.builder().setTitle(fcmVo.getTitle()).setBody(fcmVo.getBody()).build();
    Message.Builder builder = Message.builder();
    Message message = builder.setToken(getAccessToken()).setNotification(notification).build();
    return message;
  }

  public String sendMessage(Message message) throws FirebaseMessagingException {
    return this.firebaseMessaging.send(message);
  }


  private String getAccessToken() throws IOException {
    // firebase로 부터 access token을 가져온다.
    GoogleCredentials googleCredentials = GoogleCredentials
            .fromStream(new ClassPathResource("firebase/serviceAccountKey.json").getInputStream())
            .createScoped(Arrays.asList("https://www.googleapis.com/auth/cloud-platform"));
    googleCredentials.refreshIfExpired();
    String token = googleCredentials.getAccessToken().getTokenValue();

    return token;
  }

  @Transactional
  public void saveNotification(String token) {
      User user = userRepository.findByEmail(SecurityProvider.getLoginUserEmail())
              .orElseThrow(() -> new CustomException(ErrorCode.RETRY_LOGIN));
      Notification notification = Notification.builder()
              .token(token)
              .build();
      notification.confirmUser(user);
      notificationRepository.save(notification);
    }
  }

}
