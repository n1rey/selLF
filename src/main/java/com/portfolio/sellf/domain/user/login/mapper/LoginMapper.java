package com.portfolio.sellf.domain.user.login.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface LoginMapper {

  /** 로그인 **/
  UserVo tryLogin(@Param("userVo") UserVo user);
}
