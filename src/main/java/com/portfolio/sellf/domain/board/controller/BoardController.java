package com.portfolio.sellf.domain.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.portfolio.sellf.domain.board.service.BoardService;
import com.portfolio.sellf.global.common.CommonUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;
    /**
   * <pre>
   * 게시글 등록
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @RequestMapping(value = {"/insert.do"}) 
  public String boardInsertPage(HttpServletRequest request, Model model) {
    model.addAttribute("user", CommonUtil.getSessionUser(request));
    return "/board/insertForm";
  }
}
