package com.portfolio.sellf.domain.admin.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.admin.user.mapper.UserMapper;
import com.portfolio.sellf.domain.user.hide.mapper.HideMapper;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
@Service
public class UserService {

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private HideMapper hideMapper;

  /** 회원 조회 **/
  public UserVo selectUser(int userNo) {
    return userMapper.selectUser(userNo);
  }

  /** 회원 목록 조회 **/
  public List<UserVo> selectAllUser() {
    return userMapper.selectAllUser();
  }

  /** 회원 숨김 **/
  @Transactional
  public int hideUser(UserVo user) {
    int result = hideMapper.hideUser(user);

    return result;
  }
}