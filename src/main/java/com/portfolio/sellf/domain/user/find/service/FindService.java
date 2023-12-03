package com.portfolio.sellf.domain.user.find.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.find.mapper.FindMapper;

@Service
public class FindService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private FindMapper findMapper;

  /** 회원가입 **/
  @Transactional
  public int test() {
    int userNo = findMapper.test();
    return userNo;
  }
}