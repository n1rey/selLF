package com.portfolio.sellf.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Controller
@RequestMapping("/")
public class IndexController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

    /**
   * <pre>
   * 테스트
   *
   * @author 한승현
   * @date 2023/11/27
   **/
  @RequestMapping(value = {"", "/"}) 
  public String mainPage() {
    System.out.println("mainPage입니다.");
    return "/index";
  }

    /**
   * <pre>
   * 테스트
   *
   * @author 한승현
   * @date 2023/12/01
   **/
  @RequestMapping(value = {"/about"}) 
  public String aboutPage() {
    System.out.println("aboutPage입니다.");
    return "/about-me";
  }

    /**
   * <pre>
   * 로그아웃
   *
   * @author 한승현
   * @date 2023/12/07
   **/
    @RequestMapping("/logout.do") 
  public String logout(HttpServletRequest httpServletRequest) {
    HttpSession session = httpServletRequest.getSession();
    session.invalidate();
    httpServletRequest.getSession().invalidate();
    return "/index";
  }
}
