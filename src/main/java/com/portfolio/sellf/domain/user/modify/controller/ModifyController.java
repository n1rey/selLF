package com.portfolio.sellf.domain.user.modify.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.user.join.vo.UserVo;


@Controller
@RequestMapping("/modify")
public class ModifyController {

  @Value("${sellf.web.url}")
  private String SELLF_WEB_URL;

    /**
   * <pre>
   * 개인정보 수정페이지
   *
   * @author 한승현
   * @date 2023/12/11
   **/
  @RequestMapping(value = {"", "/"}) 
  public String modifyMainPage(HttpServletRequest request, Model model, HttpServletResponse response) {
    UserVo user = (UserVo) request.getSession().getAttribute("user");
    model.addAttribute("user", user);
    return "/user/modify";
  }

}
