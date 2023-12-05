package com.portfolio.sellf.domain.user.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.login.service.LoginService;

@Validated
@Controller
@RequestMapping("/login")
public class LoginController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

  @Autowired
  private LoginService loginService;
    /**
   * <pre>
   * 로그인 메인페이지
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @RequestMapping(value = {"", "/"}) 
  public String loginMainPage() {
    return "/login/login";
  }
    /**
   * <pre>
   * 회원가입
   *
   * @author 한승현
   * @date 2023/11/30
   **/
  @ResponseBody
  @PostMapping("/login.do") 
  public int submitInfo(UserVo user) {
    
    return 123;
  }
}
