package com.portfolio.sellf;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /**
   * 스프링 시큐리티 규칙 정의
   * @param http
   * @throws Exception
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
      .formLogin()
      .loginPage("/login")
      .loginProcessingUrl("/login/do/login")
      .usernameParameter("userId")
      .passwordParameter("userPassword")
      .permitAll();

    http
      .logout()
      .logoutUrl("/logout.do");

    http
      .httpBasic().disable() // 기본설정 해제
      .csrf().disable() // csrf 보안 토큰 disable처리.
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 토큰 기반 인증이므로 세션 사용안함 설정
      .and()
      .headers().frameOptions().sameOrigin()
      .and()
      .authorizeRequests()
      .antMatchers("/admin/**").hasRole("ADMIN"); //관리자만 접근가능
  }
  // 암호화에 필요한 PasswordEncoder 를 Bean 등록
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(BCryptVersion.$2B);
  }
}
