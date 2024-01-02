package com.portfolio.sellf.global.common.firebase.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.global.common.firebase.service.FCMService;
import com.portfolio.sellf.global.common.firebase.vo.FCMVo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1/norifivation")
public class FCMController {
  private final FCMService fcmService;

  @ResponseBody
  @RequestMapping(value={"", "/"})
  public String sendNotifivation(FCMVo fcmVo) {
    System.out.println("=================================="+fcmVo);
    System.out.println("=================================="+fcmVo);
    System.out.println("=================================="+fcmVo);
    System.out.println("=================================="+fcmVo);
    System.out.println("=================================="+fcmVo);
    return fcmService.sendNotificationByToken(fcmVo);
  }

  @ResponseBody
  @PostMapping("/new")
  public void saveNotification(String token) {
      fcmService.saveNotification(token);
  }
}
