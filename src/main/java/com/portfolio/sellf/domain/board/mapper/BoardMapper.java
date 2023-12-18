package com.portfolio.sellf.domain.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.board.vo.BoardVo;

@Mapper
@Repository
public interface BoardMapper {

  /** 게시글 등록 **/
  int insertBoard(@Param("boardVo") BoardVo boardVo);

  /** 게시글 조회 **/
  BoardVo selectBoard(int boardNo);

  /** 게시글 수정**/
  int updateBoard(@Param("boardVo") BoardVo boardVo);

  /** 게시글 삭제**/
  void deleteBoard(@Param("boardVo") BoardVo boardVo);

  /** 게시글 숨김**/
  int updateHideBoard(Map<String, Object> map);

  List<BoardVo> selectAllBoard();
}
