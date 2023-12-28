package com.portfolio.sellf.global.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.portfolio.sellf.global.common.log.service.LogService;

import javafx.event.ActionEvent;

@Component
public class SendMail {

  private static JavaMailSender mailSender;
  
  @Autowired
  public void MailUtil(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }
  
  @Autowired
  LogService logService;

  public static void sendMail(CommandMap map, String type) {
    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

      if(type.equals("find")) {//계정찾기
        simpleMailMessage.setTo((String)map.get("userEmail"));
        simpleMailMessage.setSubject("[selLF] 패스워드 재설정 메일");
        simpleMailMessage.setText("패스워드가 변경되었습니다. 변경된 패스워드는 "+map.get("userPassword")+" 입니다.");
      }else if(type.equals("contact")) { //관리자한테 메일발송
        //어드민 계정목록을 불러와 넣어줄거임.
        simpleMailMessage.setTo("tmdgus4720@naver.com");
        simpleMailMessage.setSubject("[selLF] "+map.get("title"));
        simpleMailMessage.setText("보낸 사람 : "+map.get("userName") +" 보낸 이메일 : "+map.get("userEmail")+"\n 내용 : "+map.get("message"));
      }else if(type.equals("schedule")){//스케쥴러
        simpleMailMessage.setTo("tmdgus4720@naver.com");
        simpleMailMessage.setSubject("[selLF] 스케쥴러 메일");
        
        simpleMailMessage.setText("비정상 시도 횟수 : "+map.get("caution")+"번 입니다. \n계정발급 횟수 : "+map.get("issue")+"번 입니다.");
      }

      Thread thread = new Thread(){
        public void run() {
          mailSender.send(simpleMailMessage);
        }
      };
      thread.start();
    }catch(Exception e) {
      e.printStackTrace();
    }
  }
}