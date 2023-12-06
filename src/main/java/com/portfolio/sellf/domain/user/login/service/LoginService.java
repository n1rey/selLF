package com.portfolio.sellf.domain.user.login.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.login.mapper.LoginMapper;
import com.portfolio.sellf.global.common.Encryption;

@Service
public class LoginService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private LoginMapper loginMapper;

  /** 로그인 **/
  @Transactional
  public int tryLogin(UserVo user) {
    String encryptPassword = Encryption.encodeSha(user.getUserPassword());
    user.setUserPassword(encryptPassword);
    UserVo userVo = loginMapper.tryLogin(user);
    int loginYn = 1;
    System.out.println(userVo);

    if(null == userVo || !userVo.getUserPassword().equals(encryptPassword)) {
      loginYn = -1;
    }
    return loginYn;
  }
}