package com.portfolio.sellf.domain.board.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.board.mapper.BoardMapper;
import com.portfolio.sellf.domain.board.vo.BoardVo;
import com.portfolio.sellf.global.common.CommandMap;

@Service
public class BoardService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private BoardMapper boardMapper;

  /** 게시글 등록 **/
  @Transactional
  public int insertBoard(BoardVo boardVo) {
    int result = boardMapper.insertBoard(boardVo);
    if(result == 1) return boardVo.getBoardNo();
    return -1;
  }

  public BoardVo selectBoard(int boardNo) {
    return boardMapper.selectBoard(boardNo);
  }

  public List<BoardVo> selectAllBoard() {
    return boardMapper.selectAllBoard();
  }

  public int updateBoard(BoardVo boardVo) {
    return boardMapper.updateBoard(boardVo);
  }

  public void deleteBoard(BoardVo boardVo) {
    boardMapper.deleteBoard(boardVo);
  }

  public int updateHideBoard(CommandMap map) {
    int result = boardMapper.updateHideBoard(map.getMap());
    return result;
  }
}