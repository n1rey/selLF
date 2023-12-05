package com.portfolio.sellf.global.security.custom;

import com.portfolio.sellf.global.security.service.CustomUserDeatailService;
import com.portfolio.sellf.global.security.vo.SecurityUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private CustomUserDeatailService customUserDeatailService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = (String)authentication.getCredentials();

    SecurityUserVo user = (SecurityUserVo) customUserDeatailService.loadUserByUsername(username);

    // password 일치하지 않으면!
    if (!passwordEncoder.matches(password, user.getPassword())){
      throw new BadCredentialsException("존재하지 않는 아이디이거나 비밀번호가 일치하지 않습니다.");
    }

    user = (SecurityUserVo) customUserDeatailService.loadUserByUserNo(Integer.toString(user.getUserNo()));

    UsernamePasswordAuthenticationToken authenticationToken
        = new UsernamePasswordAuthenticationToken(
        user,
        null,
        user.getAuthorities());

    return authenticationToken;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

}

