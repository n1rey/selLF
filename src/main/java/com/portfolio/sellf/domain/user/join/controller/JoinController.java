package com.portfolio.sellf.domain.user.join.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.user.join.service.JoinService;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommandMap;

@Controller
@RequestMapping("/join")
public class JoinController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

  @Autowired
  private JoinService joinService;

    /**
   * <pre>
   * 회원가입 메인페이지
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @RequestMapping(value = {"", "/"}) 
  public String joinMainPage() {
    return "/user/join";
  }

    /**
   * <pre>
   * 회원가입
   *
   * @author 한승현
   * @date 2023/11/30
   **/
  @ResponseBody
  @PostMapping("/submit.do") 
  public int submitInfo(UserVo user, CommandMap map) {
    return joinService.joinUser(user);
  }

    /**
   * <pre>
   * 계정발급
   *
   * @author 한승현
   * @date 2023/12/08
   **/
  @ResponseBody
  @RequestMapping("/issue.do") 
  public CommandMap issueInfo(HttpServletRequest request, CommandMap map) {
    map = joinService.beforeIssueUser(map, request);
    if(map.get("result").equals("success")) {
      map = joinService.afterIssueUser(map);
    }
    return map;
  }
}
