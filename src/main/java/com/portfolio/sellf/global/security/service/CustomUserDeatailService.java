package com.portfolio.sellf.global.security.service;

import com.portfolio.sellf.global.security.mapper.SecurityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDeatailService implements UserDetailsService {

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  SecurityMapper securityMapper;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    logger.info("===== loadUserByUsername ====");

    UserDetails userDetails = securityMapper.selectOneUserByUserId(userId);

    if (userDetails == null) {
      throw new UsernameNotFoundException("존재하지 않는 아이디이거나 비밀번호가 일치하지 않습니다.");
    }

    return userDetails;
  }

  public UserDetails loadUserByUserNo(String userNo) throws UsernameNotFoundException {
    UserDetails userDetails = securityMapper.selectOneUserByUserNo(userNo);
    if (userDetails == null) {
      throw new UsernameNotFoundException("존재하지 않는 아이디이거나 비밀번호가 일치하지 않습니다.");
    }
    return userDetails;
  }
}
