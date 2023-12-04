package com.portfolio.sellf.domain.user.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface LoginMapper {

  /** 회원가입 **/
  int insertUser(UserVo user);
}
