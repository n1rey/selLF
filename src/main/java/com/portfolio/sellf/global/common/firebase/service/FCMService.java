package com.portfolio.sellf.global.common.firebase.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.portfolio.sellf.global.common.firebase.mapper.FCMMapper;
import com.portfolio.sellf.global.common.firebase.vo.TokenVo;
import com.portfolio.sellf.global.common.util.CommandMap;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMService {

  private final FirebaseMessaging firebaseMessaging;

  @Autowired
  private FCMMapper fcmMapper;

  public int insertToken(TokenVo tokenVo) {
    return fcmMapper.insertToken(tokenVo);
  }

  public List<String> selectTokenList() {
    return fcmMapper.selectTokenList();
  }

  public String sendNotificationByToken(CommandMap commandMap) {
    try {
      pushAlarm(commandMap);
      return "알림을 성공적으로 전송했습니다.";
    } catch (Exception e) {
      e.printStackTrace();
      return "알림 보내기를 실패했습니다.";
    }
  }

  private void pushAlarm(CommandMap commandMap) throws FirebaseMessagingException, IOException, FirebaseAuthException {
    Message message = getMessage(commandMap);
    sendMessage(message);
  }

  private Message getMessage(CommandMap commandMap) throws IOException, FirebaseAuthException {
    Notification notification = Notification.builder().setTitle(commandMap.get("title").toString()).setBody(commandMap.get("body").toString()).build();
    Message.Builder builder = Message.builder();

    Message message = builder.setToken(commandMap.get("token").toString()).setNotification(notification).build();
    return message;
  }

  public String sendMessage(Message message) throws FirebaseMessagingException {
    System.out.println("=========================================================================================");
    return this.firebaseMessaging.send(message);
  }
}