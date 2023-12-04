package com.portfolio.sellf.domain.user.join.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.common.CommandMap;
import com.portfolio.sellf.domain.user.join.service.JoinService;
import com.portfolio.sellf.domain.user.join.vo.UserVo;

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
   * 회원가입
   *
   * @author 한승현
   * @date 2023/11/30
   **/
  @ResponseBody
  @PostMapping("/submitInfo") 
  public int submitInfo(CommandMap map) {
    System.out.println(map.toString());
    // return joinService.insertUser(user);
    return 123;
  }
}
