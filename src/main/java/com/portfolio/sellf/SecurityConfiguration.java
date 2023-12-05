package com.portfolio.sellf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.portfolio.sellf.global.security.custom.CustomAuthenticationFailureHandler;
import com.portfolio.sellf.global.security.custom.CustomAuthenticationSuccessHandler;
import com.portfolio.sellf.global.security.service.CustomUserDeatailService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /**
   * 스프링 시큐리티 규칙 정의
   * @param http
   * @throws Exception
   */
  @Autowired
  CustomUserDeatailService customUserDetailsService;

  @Autowired
  private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

  @Autowired
  private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(customUserDetailsService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
      .formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/login/do/login")
      .usernameParameter("userId")
      .passwordParameter("userPassword")
      .successHandler(customAuthenticationSuccessHandler)
      .failureHandler(customAuthenticationFailureHandler)
      .permitAll();

    http
      .logout()
      .logoutUrl("/logout.do")
      .logoutSuccessUrl("/");

    http
      .httpBasic().disable() // 기본설정 해제
      .csrf().disable() // csrf 보안 토큰 disable처리.
      .headers().frameOptions().sameOrigin()
      .and()
      .authorizeRequests()
      .antMatchers("/","/join/**","/find/**").permitAll()
      .antMatchers("/admin/**").hasRole("ADMIN"); //관리자만 접근가능
  }

  // 암호화에 필요한 PasswordEncoder 를 Bean 등록
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(BCryptVersion.$2B);
  }
}