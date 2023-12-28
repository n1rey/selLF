package com.portfolio.sellf.domain.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.admin.code.service.CodeService;
import com.portfolio.sellf.domain.board.service.BoardService;
import com.portfolio.sellf.domain.board.vo.BoardVo;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.CommonUtil;

@Controller
@RequestMapping("/board")
public class BoardController {

  @Autowired
  private BoardService boardService;

  @Autowired
  private CodeService codeService;

    /**
   * <pre>
   * 게시글 등록페이지
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @RequestMapping(value = {"/admin"}) 
  public String boardInsertPage(HttpServletRequest request, Model model, CommandMap map) {
    UserVo user = CommonUtil.getSessionUser(request);
    if(user == null) return "redirect:/";
    if(map.get("boardNo") != null) {
      model.addAttribute("boardNo", map.get("boardNo").toString());
      model.addAttribute("status", "update");
    }
    model.addAttribute("user", user);
    model.addAttribute("categoryList", codeService.getCode("project_category").split(","));
    return "/board/insertForm";
  }

    /**
   * <pre>
   * 게시글 페이지
   *
   * @author 한승현
   * @date 2023/12/13
   **/
  @RequestMapping(value = {"/view/{boardNo}"}) 
  public String boardView(HttpServletRequest request, Model model, @PathVariable int boardNo) {
    UserVo user = CommonUtil.getSessionUser(request);
    if(user != null) {
      model.addAttribute("userRole", user.getUserRole());
    }
    BoardVo boardVo = boardService.selectBoard(boardNo);
    if(boardVo != null){
      model.addAttribute("boardHideYn", boardVo.getBoardHideYn());
    }
    model.addAttribute("boardNo", boardNo);
    return "/board/viewForm";
  }

    /**
   * <pre>
   * 게시글 정보조회
   *
   * @author 한승현
   * @date 2023/12/14
   **/
  @ResponseBody
  @RequestMapping(value = {"/getBoard/{boardNo}"}) 
  public BoardVo boardInfo(HttpServletRequest request, Model model, @PathVariable int boardNo) {
    BoardVo boardVo = boardService.selectBoard(boardNo);
    return boardVo;
  }
  
    /**
   * <pre>
   * 게시글 등록
   *
   * @author 한승현
   * @date 2023/12/14
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
   * @date 2023/12/14
   **/
  @ResponseBody
  @RequestMapping(value = {"/admin/updateBoard.do"}) 
  public int updateBoard(HttpServletRequest request, Model model, BoardVo boardVo) {
    int result = boardService.updateBoard(boardVo);
    return result;
  }
  
    /**
   * <pre>
   * 게시글 삭제
   *
   * @author 한승현
   * @date 2023/12/14
   **/
  @RequestMapping(value = {"/admin/delete.do"}) 
  public String deleteBoard(HttpServletRequest request, Model model, BoardVo boardVo) {
    boardService.deleteBoard(boardVo);
    return "redirect:/";
  }
  
    /**
   * <pre>
   * 게시글 숨김
   *
   * @author 한승현
   * @date 2023/12/14
   **/
  @ResponseBody
  @RequestMapping(value = {"/admin/hide.do"}) 
  public int updateHideBoard(HttpServletRequest request, Model model, CommandMap map) {
    int result = boardService.updateHideBoard(map);
    return result;
  }
}
