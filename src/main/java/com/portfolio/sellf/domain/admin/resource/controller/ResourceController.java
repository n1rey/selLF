package com.portfolio.sellf.domain.admin.resource.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.portfolio.sellf.global.common.CommandMap;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController {


    /**
   * <pre>
   * 리소스페이지
   *
   * @author 한승현
   * @date 2023/12/20
   **/
  @RequestMapping(value = {"", "/"}) 
  public String resourcePage(HttpServletRequest request, Model model, CommandMap map) {
    System.out.println();

    return "/admin/resource";
  }
}
