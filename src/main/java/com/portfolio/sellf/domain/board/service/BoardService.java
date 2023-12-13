package com.portfolio.sellf.domain.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.board.mapper.BoardMapper;

@Service
public class BoardService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private BoardMapper boardMapper;

  /** 회원가입 **/
  @Transactional
  public int test() {
    int userNo = boardMapper.test();
    return userNo;
  }
}