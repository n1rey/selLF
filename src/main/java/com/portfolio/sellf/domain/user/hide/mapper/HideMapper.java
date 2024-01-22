package com.portfolio.sellf.domain.user.hide.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface HideMapper {

  /** 회원정보 숨김 **/
  int hideUser(@Param("userVo") UserVo user);

}
