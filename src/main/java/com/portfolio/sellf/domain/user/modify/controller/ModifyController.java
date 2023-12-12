package com.portfolio.sellf.domain.user.modify.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.modify.service.ModifyService;
import com.portfolio.sellf.global.common.CommandMap;
import com.portfolio.sellf.global.common.log.vo.LogVo;


@Controller
@RequestMapping("/modify")
public class ModifyController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

  @Autowired
  private ModifyService modifyService;
  
    /**
   * <pre>
   * 개인정보 체크페이지
   *
   * @author 한승현
   * @date 2023/12/11
   **/
  @RequestMapping(value = {"", "/"}) 
  public String modifyCheckPage(HttpServletRequest request, HttpServletResponse response) {
    return "/user/modify-check";
  }

      /**
   * <pre>
   * 패스워드 검증
   *
   * @author 한승현
   * @date 2023/12/12
   **/
  @RequestMapping("/checkPassword.do") 
  public String checkPassword(HttpServletRequest request, CommandMap map, Model model) {
    String message = modifyService.checkPassword(request, map);
    if(message.equals("")) {
      UserVo user = (UserVo) request.getSession().getAttribute("user");
      user.setUserPassword("");
      model.addAttribute("user", user);
      return "/user/modifyForm";
    }else {
      model.addAttribute("message", message);
      return "/user/modify-check";
    }
  }

      /**
   * <pre>
   * 회원정보 수정
   *
   * @author 한승현
   * @date 2023/12/12
   **/
  @ResponseBody
  @PostMapping("/submit.do") 
  public int submitInfo(UserVo user) {
    int result = modifyService.updateUser(user);
    return result;
  }

}
