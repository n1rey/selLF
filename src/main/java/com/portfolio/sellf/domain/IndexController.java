package com.portfolio.sellf.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.admin.code.service.CodeService;
import com.portfolio.sellf.domain.board.service.BoardService;

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
    model.addAttribute("categoryList", codeService.getCode("project_category").split(","));
    return "/index";
  }

    /**
   * <pre>
   * 테스트
   *
   * @author 한승현
   * @date 2023/12/01
   **/
  @RequestMapping(value = {"/about"}) 
  public String aboutPage() {
    System.out.println("aboutPage입니다.");
    return "/about-me";
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
