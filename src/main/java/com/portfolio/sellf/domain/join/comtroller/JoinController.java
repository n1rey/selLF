package com.portfolio.sellf.domain.join.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.join.service.JoinService;

@Validated
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
    
    return "/join/join";
  }

    /**
   * <pre>
   * DB연동 테스트
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @RequestMapping(value = {"/test"}) 
  public String testPage() {
    System.out.println(joinService.test());
    return "/join/join";
  }
}
