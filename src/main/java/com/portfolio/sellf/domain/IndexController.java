package com.portfolio.sellf.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.admin.code.service.CodeService;
import com.portfolio.sellf.domain.board.service.BoardService;
import com.portfolio.sellf.global.common.util.CommandMap;

@Controller
@RequestMapping("/")
public class IndexController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private CodeService codeService;

    /**
   * <pre>
   * 메인페이지
   *
   * @author 한승현
   * @date 2023/11/27
   **/
  @RequestMapping(value = {"", "/"}) 
  public String mainPage(HttpServletRequest request, Model model) {
    model.addAttribute("boardList", boardService.selectAllBoard());
    model.addAttribute("categoryList", codeService.getCode("projectCategory").split(","));
    return "/index";
  }

    /**
   * <pre>
   * about페이지
   *
   * @author 한승현
   * @date 2023/12/28
   **/
  @RequestMapping(value = {"/about"}) 
  public String aboutPage(HttpServletRequest request, Model model) {
    // model.addAttribute("projectCnt", codeService.getCode("projectCnt"));
    return "/about-me";
  }

    /**
   * <pre>
   * history페이지
   *
   * @author 한승현
   * @date 2023/12/28
   **/
  @RequestMapping(value = {"/history"}) 
  public String historyPage(HttpServletRequest request, Model model) {
    return "/history";
  }

    /**
   * <pre>
   * 로그아웃
   *
   * @author 한승현
   * @date 2023/12/07
   **/
  @RequestMapping("/logout.do")
  public String logout(HttpServletRequest httpServletRequest) {
    HttpSession session = httpServletRequest.getSession();
    session.invalidate();
    httpServletRequest.getSession().invalidate();
    return "redirect:/";
  }
}