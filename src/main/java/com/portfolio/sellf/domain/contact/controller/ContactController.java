package com.portfolio.sellf.domain.contact.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.portfolio.sellf.domain.contact.service.ContactService;

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
    
    return "/user/contact";
  }

    /**
   * <pre>
   * 소통 - 메일발송
   *
   * @author 한승현
   * @date 2023/11/29
   **/
  @PostMapping("/sendMail")
  public String contactSendMail(HttpServletRequest request, 
    @RequestParam(value = "name", required = false) String name,
    @RequestParam(value = "email", required = false) String email) {
    System.out.println(name+email);
    return "redirect:/contact";
  }
}
