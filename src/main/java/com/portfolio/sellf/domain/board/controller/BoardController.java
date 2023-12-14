package com.portfolio.sellf.domain.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.board.service.BoardService;
import com.portfolio.sellf.domain.board.vo.BoardVo;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommonUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;

    /**
   * <pre>
   * 게시글 페이지
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @RequestMapping(value = {"/admin"}) 
  public String boardInsertPage(HttpServletRequest request, Model model) {
    UserVo user = CommonUtil.getSessionUser(request);
    if(user == null) return "redirect:/";

    model.addAttribute("user", user);
    return "/board/insertForm";
  }

    /**
   * <pre>
   * 게시글 보기
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @RequestMapping(value = {"/view/{boardNo}"}) 
  public String boardView(HttpServletRequest request, Model model, BoardVo board, @PathVariable int boardNo) {
    //model.addAttribute("userNo", CommonUtil.getSessionUser(request).getUserNo());
    //BoardVo boardVo = boardService.selectBoard(boardNo);
    model.addAttribute("boardNo", boardNo);
    return "/board/view";
  }

    /**
   * <pre>
   * 게시글 보기
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @ResponseBody
  @RequestMapping(value = {"/getBoard/{boardNo}"}) 
  public BoardVo boardInfo(HttpServletRequest request, Model model, BoardVo board, @PathVariable int boardNo) {
    BoardVo boardVo = boardService.selectBoard(boardNo);
    return boardVo;
  }
  
    /**
   * <pre>
   * 게시글 등록
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @ResponseBody
  @RequestMapping(value = {"/admin/insertBoard.do"}) 
  public int insertBoard(HttpServletRequest request, Model model, BoardVo boardVo) {
    int boardNo = boardService.insertBoard(boardVo);
    return boardNo;
  }
  
    /**
   * <pre>
   * 게시글 수정
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @ResponseBody
  @RequestMapping(value = {"/admin/updateBoard.do"}) 
  public int updateBoard(HttpServletRequest request, Model model, BoardVo board) {
    int result = 1;
    return result;
  }
  
    /**
   * <pre>
   * 게시글 삭제, 수정
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @ResponseBody
  @RequestMapping(value = {"/admin/statusBoard.do"}) 
  public int statusBoard(HttpServletRequest request, Model model, BoardVo board) {
    int result = 1;
    return result;
  }
}
