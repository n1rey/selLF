package com.portfolio.sellf.domain.join.comtroller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@Controller
@RequestMapping("/join")
public class JoinController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

    /**
   * <pre>
   * 테스트
   *
   * @author 한승현
   * @date 2022/01/13
   **/
  @RequestMapping(value = {"", "/"}) 
  public String joinMainPage() {
    System.out.println("hihihi");
    return "/index";
  }
}
