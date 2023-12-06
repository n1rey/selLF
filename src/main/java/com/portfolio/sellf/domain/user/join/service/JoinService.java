package com.portfolio.sellf.domain.user.join.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.mapper.JoinMapper;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommandMap;
import com.portfolio.sellf.global.common.Encryption;

@Service
public class JoinService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JoinMapper joinMapper;

  /** 회원가입 **/
  @Transactional
  public int joinUser(UserVo user) {
    String encryptPassword = Encryption.encodeSha(user.getUserPassword());
    user.setUserPassword(encryptPassword);

    //유저ID가 겹치는지 확인
    CommandMap map = new CommandMap();
    map.put("userid", user.getUserId());
    if(checkId(map) > 0) {
      return -999;
    }
    
    int userNo = joinMapper.joinUser(user);

    return userNo;
  }

  /**아이디 중복체크**/
  public int checkId(CommandMap map) {
    return joinMapper.checkId(map.getMap());
  }
}