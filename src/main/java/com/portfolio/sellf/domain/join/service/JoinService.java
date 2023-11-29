package com.portfolio.sellf.domain.join.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.join.mapper.JoinMapper;

@Service
public class JoinService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JoinMapper joinMapper;

  /** 회원가입 **/
  @Transactional
  public int test() {
    int userNo = joinMapper.test();
    return userNo;
  }
}