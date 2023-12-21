package com.portfolio.sellf.domain.user.find.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.find.mapper.FindMapper;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.util.Encryption;
import com.portfolio.sellf.global.common.util.RandomInfo;
import com.portfolio.sellf.global.common.util.SendMail;

@Service
public class FindService {

  @Autowired
  private FindMapper findMapper;

  /** 계정찾기 **/
  @Transactional
  public int searchInfo(UserVo user) {
    UserVo userInfo = findMapper.searchInfo(user);
    int result = -1;
    
    if(userInfo != null) {
      String password = RandomInfo.randomPassword();
      user.setUserPassword(password);
      SendMail.sendMail(user, "find");
      password = Encryption.encodeSha(password);
      user.setUserPassword(password);
      findMapper.updatePassword(user);
      result = 1;
    }
    return result;
  }
}