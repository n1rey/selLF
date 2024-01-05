package com.portfolio.sellf.global.common.firebase.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.firebase.mapper.FCMMapper;
import com.portfolio.sellf.global.common.firebase.vo.TokenVo;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.CommonUtil;

import ch.qos.logback.core.subst.Token;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMService {

  private final FirebaseMessaging firebaseMessaging;

  @Autowired
  private FCMMapper fcmMapper;

  //토큰 추가
  public int insertToken(TokenVo tokenVo) {
    return fcmMapper.insertToken(tokenVo);
  }

  //토큰을 가지고있는 유저 불러오기
  public List<String> selectUserList(CommandMap map) {
    return fcmMapper.selectUserList(map.getMap());
  }

  //토큰체크
  public List<TokenVo> checkToken(CommandMap map, HttpServletRequest request) {
    List<TokenVo> tokenList = fcmMapper.checkToken(map.getMap());
    UserVo user = CommonUtil.getSessionUser(request);
    if(user != null) {
      for(TokenVo tokenVo : tokenList) {
        //DB에 저장되어있는 토큰의 userId와 로그인한 유저가 다를경우 업데이트.
        if(!tokenVo.getUserId().equals(user.getUserId())) {
          tokenVo.setUserId(user.getUserId());
          fcmMapper.updateToken(tokenVo);
        }
      }
    }
    return tokenList;
  }

  //계정에 저장되어있는 토큰에 푸시
  public String sendNotificationByUser(CommandMap commandMap) {
    try {
      return pushAlarm(commandMap);
    } catch (Exception e) {
      e.printStackTrace();
      return "알림 보내기를 실패했습니다.";
    }
  }

  //토큰에 푸시
  public String sendNotificationByToken(CommandMap commandMap) {
    try {
      return pushAlarm2(commandMap);
    } catch (Exception e) {
      e.printStackTrace();
      return "알림 보내기를 실패했습니다.";
    }
  }

  //유저 푸시
  private String pushAlarm(CommandMap commandMap) throws FirebaseMessagingException, IOException, FirebaseAuthException {
    List<String> userTokenList = fcmMapper.selectUserTokenList(commandMap.getMap());
    int expireTokenCnt = 0;
    if(userTokenList.size() == 0) return "유저 또는 토큰이 존재하지 않습니다.";
    for(String userToken : userTokenList) {
      commandMap.put("token", userToken);
      try {
        Message message = getMessage(commandMap);
        sendMessage(message);
      } catch (Exception e) {
        //만료 시 익셉션 발생. 만료토큰은 삭제.
        fcmMapper.deleteToken(userToken);
        expireTokenCnt++;
      }
    }
    return "푸시 토큰 : " + (userTokenList.size()-expireTokenCnt) +"개"+" 만료 토큰 : "+expireTokenCnt+"개";
  }

  //토큰 푸시
  private String pushAlarm2(CommandMap commandMap) throws FirebaseMessagingException, IOException, FirebaseAuthException {
    Message message = getMessage(commandMap);
    sendMessage(message);
    return "알림보내기를 성공 했습니다.";
  }

  //푸시 빌더
  private Message getMessage(CommandMap commandMap) throws IOException, FirebaseAuthException {
    Notification notification = Notification.builder().setTitle(commandMap.get("title").toString()).setBody(commandMap.get("body").toString()).build();
    Message.Builder builder = Message.builder();

    Message message = builder.setToken(commandMap.get("token").toString()).setNotification(notification).build();
    return message;
  }

  //푸시 전송
  public String sendMessage(Message message) throws FirebaseMessagingException {
    return this.firebaseMessaging.send(message);
  }

  //토큰Vo 생성
  public TokenVo makeTokenVo(CommandMap commandMap, HttpServletRequest request) {
    TokenVo tokenVo = new TokenVo();
    tokenVo.setTokenId(commandMap.get("tokenId").toString());
    UserVo user = CommonUtil.getSessionUser(request);
    if(user != null) {
      tokenVo.setUserId(user.getUserId());
    }
    return tokenVo;
  }
}