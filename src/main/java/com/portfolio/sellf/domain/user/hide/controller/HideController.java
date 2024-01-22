package com.portfolio.sellf.domain.user.hide.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.user.hide.service.HideService;
import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Controller
@RequestMapping("/hide")
public class HideController {

  @Autowired
  private HideService hideService;
  
    /**
   * <pre>
   * 회원 숨김
   *
   * @author 변예린
   * @date 2023/01/22
   **/
  @ResponseBody
  @PostMapping("/submit.do") 
  public int submitInfo(UserVo user) {
    int result = hideService.hideUser(user);
    return result;
  }
}
