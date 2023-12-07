package com.portfolio.sellf.domain.user.find.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.user.find.service.FindService;
import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Validated
@Controller
@RequestMapping("/find")
public class FindController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

  @Autowired
  private FindService findService;
    /**
   * <pre>
   * 계정찾기 페이지
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @RequestMapping(value = {"", "/"}) 
  public String findMainPage() {
    return "/user/find";
  }

    /**
   * <pre>
   * 계정찾기
   *
   * @author 한승현
   * @date 2023/11/30
   **/
  @ResponseBody
  @RequestMapping("/searchInfo.do") 
  public int searchInfo(UserVo user) {
    int result = findService.searchInfo(user);
    return result;
  }
}
