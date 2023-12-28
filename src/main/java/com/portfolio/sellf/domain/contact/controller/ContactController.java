package com.portfolio.sellf.domain.contact.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.sellf.domain.contact.service.ContactService;
import com.portfolio.sellf.global.common.util.CommandMap;

@Controller
@RequestMapping("/contact")
public class ContactController {

  @Autowired
  private ContactService ContactService;
    /**
   * <pre>
   * 소통 메인페이지
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @RequestMapping(value = {"", "/"}) 
  public String contactMainPage() {
    
    return "/contact/contact";
  }

    /**
   * <pre>
   * 소통 - 메일발송
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @PostMapping("/sendMail")
  public String contactSendMail(HttpServletRequest request, CommandMap map) {
    return "redirect:/contact";
  }
}
