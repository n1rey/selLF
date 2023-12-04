package com.portfolio.sellf.domain.user.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.login.mapper.LoginMapper;

@Service
public class LoginService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private LoginMapper loginMapper;

  @Autowired
  public PasswordEncoder passwordEncoder;

  /** 회원가입 **/
  @Transactional
  public int insertUser(UserVo user) {
    String encryptPassword = passwordEncoder.encode(user.getUserPassword());
    user.setUserPassword(encryptPassword);
    int userNo = loginMapper.insertUser(user);
    return userNo;
  }
}