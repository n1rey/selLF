package com.portfolio.sellf.domain.user.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.login.mapper.LoginMapper;
import com.portfolio.sellf.global.common.Encryption;

@Service
public class LoginService {

  @Autowired
  private LoginMapper loginMapper;

  /** 로그인 **/
  @Transactional
  public int tryLogin(UserVo user) {
    String encryptPassword = Encryption.encodeSha(user.getUserPassword());
    user.setUserPassword(encryptPassword);
    UserVo userVo = loginMapper.tryLogin(user);
    int loginYn = 1;

    if(null == userVo || !userVo.getUserPassword().trim().equals(encryptPassword.trim())) {
      loginYn = -1;
    }
    return loginYn;
  }
}