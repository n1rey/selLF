package com.portfolio.sellf.domain;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.board.service.BoardService;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommonUtil;

@Controller
@RequestMapping("/")
public class IndexController {

  @Autowired
  private BoardService boardService;
    /**
   * <pre>
   * 메인페이지
   *
   * @author 한승현
   * @date 2023/11/27
   **/
  @RequestMapping(value = {"", "/"}) 
  public String mainPage(HttpServletRequest request, Model model) {
    UserVo user = CommonUtil.getSessionUser(request);
    if(user != null) {
      model.addAttribute("userRole", user.getUserRole());
    }
    model.addAttribute("boardList", boardService.selectAllBoard());
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
    return "/index";
  }
}
