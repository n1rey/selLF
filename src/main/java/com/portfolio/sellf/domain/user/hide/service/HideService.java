package com.portfolio.sellf.domain.user.hide.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.hide.mapper.HideMapper;
import com.portfolio.sellf.global.common.util.CommonUtil;
import com.portfolio.sellf.global.common.util.Encryption;

@Service
public class HideService {

  @Autowired
  private HideMapper hideMapper;

  /** 회원 숨김 **/
  @Transactional
  public int hideUser(UserVo user) {
    if(CommonUtil.checkNull(user.getUserPassword())){
      String encryptPassword = Encryption.encodeSha(user.getUserPassword());
      user.setUserPassword(encryptPassword);
    }
    int result = hideMapper.hideUser(user);

    return result;
  }
}