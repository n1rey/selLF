package com.portfolio.sellf.domain.admin.code.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.admin.code.service.CodeService;
import com.portfolio.sellf.domain.admin.code.vo.CodeVo;
import com.portfolio.sellf.global.common.CommandMap;
import com.portfolio.sellf.global.common.CommonUtil;

@Controller
@RequestMapping("/admin/code")
public class CodeController {

  @Autowired
  CodeService codeService;

    /**
   * <pre>
   * 코드페이지
   *
   * @author 한승현
   * @date 2023/12/21
   **/
  @RequestMapping(value = {"", "/"}) 
  public String codePage(HttpServletRequest request, Model model, CommandMap map) {
    model.addAttribute("codeList", codeService.getCodeList());
    model.addAttribute("userNo", CommonUtil.getSessionUser(request).getUserNo());
    return "/admin/code";
  }

    /**
   * <pre>
   * 코드등록
   *
   * @author 한승현
   * @date 2023/12/21
   **/
  @ResponseBody
  @RequestMapping(value = "/insert.do") 
  public int insertCode(HttpServletRequest request, Model model, CodeVo codeVo) {
    return codeService.insertCode(codeVo);
  }
}
