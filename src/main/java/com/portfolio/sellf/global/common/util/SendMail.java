package com.portfolio.sellf.global.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

import javafx.event.ActionEvent;

@Component
public class SendMail {

  private static JavaMailSender mailSender;
  
  @Autowired
  public void MailUtil(JavaMailSender mailSender) {
    this.mailSender = mailSender;
  }
  
  public static void sendMail(UserVo user, String type) {
    try {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

      if(type.equals("find")) {//계정찾기
        simpleMailMessage.setTo(user.getUserEmail());
        simpleMailMessage.setSubject("[selLF] 패스워드 재설정 메일");
        simpleMailMessage.setText("패스워드가 변경되었습니다. 변경된 패스워드는 "+user.getUserPassword()+" 입니다.");
        System.out.println("계정찾기");
      }else if(type.equals("contact")) { //관리자한테 메일발송
        //어드민 계정목록을 불러와 넣어줄거임.
        simpleMailMessage.setTo("tmdgus4720@naver.com");
        simpleMailMessage.setSubject("[selLF] "+user.getUserId());
        //title : user.getUserId()
        //contents : user.getUserProfileImage()
        simpleMailMessage.setText("보낸 사람 : "+user.getUserName() +" 보낸 이메일 : "+user.getUserEmail()+"\n 내용 : "+user.getUserProfileImage());
        System.out.println("contact");
      }else if(type.equals("schedule")){//스케쥴러
        simpleMailMessage.setTo("tmdgus4720@naver.com");
        simpleMailMessage.setSubject("[selLF] 스케쥴러 메일");
        simpleMailMessage.setText("총 접속 수 : "+user.getUserProfileImage()+" 명 입니다. \n비정상 시도 횃수 : "+user.getUserId()+"번 입니다.");
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