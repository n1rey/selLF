package com.portfolio.sellf.domain.user.join.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.mapper.JoinMapper;
import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Service
public class JoinService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JoinMapper joinMapper;

  @Autowired
  public PasswordEncoder passwordEncoder;

  /** 회원가입 **/
  @Transactional
  public int insertUser(UserVo user) {
    String encryptPassword = passwordEncoder.encode(user.getUserPassword());
    user.setUserPassword(encryptPassword);
    int userNo = joinMapper.insertUser(user);
    return userNo;
  }
}