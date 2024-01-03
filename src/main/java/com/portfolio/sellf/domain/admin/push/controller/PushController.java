package com.portfolio.sellf.domain.admin.push.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.global.common.firebase.service.FCMService;
import com.portfolio.sellf.global.common.util.CommandMap;

@Controller
@RequestMapping("/admin/push")
public class PushController {

  @Autowired
  FCMService fcmService;

    /**
   * <pre>
   * 푸시페이지
   *
   * @author 한승현
   * @date 2024/01/03
   **/
  @RequestMapping(value = {"", "/"}) 
  public String pushPage(HttpServletRequest request, Model model, CommandMap map) {
    model.addAttribute("tokenList", fcmService.selectTokenList());
    return "/admin/push";
  }

    /**
   * <pre>
   * 단일푸시
   *
   * @author 한승현
   * @date 2024/01/03
   **/
  @ResponseBody
  @RequestMapping(value = "/send.do") 
  public String sendPush(HttpServletRequest request, Model model, CommandMap map) {
    try {
      fcmService.sendNotificationByToken(map);
      return "1";
    } catch (Exception e) {
      return "2";
    }
  }

    /**
   * <pre>
   * 토큰보기
   *
   * @author 한승현
   * @date 2024/01/03
   **/
  @ResponseBody
  @RequestMapping(value = "/show.do") 
  public void showToken(HttpServletRequest request, Model model, CommandMap map) {
    System.out.println(map.get("tokenId").toString());
  }
}