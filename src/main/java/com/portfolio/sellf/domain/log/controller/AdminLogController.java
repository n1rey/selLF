package com.portfolio.sellf.domain.log.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.log.service.AdminLogService;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.CommonUtil;

@Controller
@RequestMapping("/admin/log")
public class AdminLogController {

  @Autowired
  private AdminLogService logService;

    /**
   * <pre>
   * 로그 페이지
   *
   * @author 변예린
   * @date 2024/01/04
   **/

  @RequestMapping(value = {"", "/"}) 
  public String logPage(HttpServletRequest request, Model model, CommandMap map) {
    model.addAttribute("logList", logService.selectAllLog());
    model.addAttribute("userNo", CommonUtil.getSessionUser(request).getUserNo());
    return "/admin/log";
  }

}
