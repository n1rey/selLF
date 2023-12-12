package com.portfolio.sellf.domain.user.modify.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.modify.mapper.ModifyMapper;
import com.portfolio.sellf.global.common.Encryption;

@Service
public class ModifyService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ModifyMapper modifyMapper;


  /** 회원정보수정 **/
  @Transactional
  public int updateUser(UserVo user) {
    if(user.getUserPassword() != null){
      String encryptPassword = Encryption.encodeSha(user.getUserPassword());
      user.setUserPassword(encryptPassword);
    }
    
    int result = modifyMapper.updateUser(user);

    return result;
  }
}