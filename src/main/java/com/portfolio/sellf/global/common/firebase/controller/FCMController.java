package com.portfolio.sellf.global.common.firebase.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.firebase.service.FCMService;
import com.portfolio.sellf.global.common.firebase.vo.TokenVo;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.CommonUtil;

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
    TokenVo tokenVo = new TokenVo();
    tokenVo.setTokenId(commandMap.get("tokenId").toString());
    UserVo user = CommonUtil.getSessionUser(request);
    if(user != null) {
      tokenVo.setTokenId(user.getUserId());
    }
    System.out.println("========================="+tokenVo.toString());
    return fcmService.insertToken(tokenVo);
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
  public void tokenShow(HttpServletRequest request, CommandMap commandMap) {
    System.out.println("========================="+commandMap.toString());
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