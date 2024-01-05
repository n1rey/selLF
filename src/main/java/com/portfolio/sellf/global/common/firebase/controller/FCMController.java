package com.portfolio.sellf.global.common.firebase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.global.common.firebase.service.FCMService;
import com.portfolio.sellf.global.common.util.CommandMap;

@Controller
@RequestMapping("/token")
public class FCMController {

  @Autowired
  FCMService fcmService;

    /**
   * <pre>
   * 토큰저장
   *
   * @author 한승현
   * @date 2024/01/03
   **/
  @ResponseBody
  @RequestMapping(value = "/save.do") 
  public int tokenSave(HttpServletRequest request, CommandMap commandMap) {
    System.out.println("========================="+commandMap.toString());

    return fcmService.insertToken(fcmService.makeTokenVo(commandMap, request));
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
  public String tokenShow(HttpServletRequest request, CommandMap commandMap) {
    if(fcmService.checkToken(commandMap, request).size() == 0) {
      fcmService.insertToken(fcmService.makeTokenVo(commandMap, request));
    }
    System.out.println("=========================================="+commandMap.toString());
    return "1";
  }

    /**
   * <pre>
   * 토큰조회
   *
   * @author 한승현
   * @date 2024/01/03
   **/
  @ResponseBody
  @RequestMapping(value = "/search.do") 
  public String searchToken(HttpServletRequest request, CommandMap commandMap) {
    System.out.println(commandMap);
    return "";
  }
}