package com.portfolio.sellf.domain.admin.resource.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.admin.resource.service.ResourceService;
import com.portfolio.sellf.global.common.CommandMap;

@Controller
@RequestMapping("/admin/resource")
public class ResourceController {

  @Autowired
  ResourceService resourceService;

    /**
   * <pre>
   * 리소스페이지
   *
   * @author 한승현
   * @date 2023/12/20
   **/
  @RequestMapping(value = {"", "/"}) 
  public String resourcePage(HttpServletRequest request, Model model, CommandMap map) {
    model.addAttribute("disk", resourceService.getDiskSpace(request));
    model.addAttribute("cpu", resourceService.getCPUProcess(request));
    model.addAttribute("memory", resourceService.getMemory());

    return "/admin/resource";
  }
}
