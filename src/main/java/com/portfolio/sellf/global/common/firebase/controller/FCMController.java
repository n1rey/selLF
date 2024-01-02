package com.portfolio.sellf.global.common.firebase.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.sellf.global.common.firebase.service.FCMService;
import com.portfolio.sellf.global.common.firebase.vo.FcmMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/norifivation")
public class FCMController {
  private final FCMService fcmService;

  @GetMapping
  public String sendNotifivation(@RequestBody FcmMessage fcmVo) {
    return fcmService.sendNotificationByToken(fcmVo);
  }
}
