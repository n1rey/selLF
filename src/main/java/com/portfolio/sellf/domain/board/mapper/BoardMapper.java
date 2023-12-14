package com.portfolio.sellf.domain.board.mapper;

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
}
