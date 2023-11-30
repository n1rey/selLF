package com.portfolio.sellf.domain.user.join.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface JoinMapper {

  /** 회원가입 **/
  int insertUser(UserVo user);
}
