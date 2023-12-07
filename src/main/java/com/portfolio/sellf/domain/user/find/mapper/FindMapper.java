package com.portfolio.sellf.domain.user.find.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface FindMapper {

  /** 정보찾기 **/
  UserVo searchInfo(@Param("userVo") UserVo user);

  /** 패스워드 재발급 **/
  void updatePassword(@Param("userVo") UserVo user);
}
