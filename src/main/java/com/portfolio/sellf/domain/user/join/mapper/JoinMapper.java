package com.portfolio.sellf.domain.user.join.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface JoinMapper {

  /** 회원가입 **/
  int joinUser(@Param("userVo") UserVo user);

  /** 아이디 중복검사 **/
  int checkId(Map<String, Object> map);
}
