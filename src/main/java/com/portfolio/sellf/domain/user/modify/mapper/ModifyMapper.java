package com.portfolio.sellf.domain.user.modify.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface ModifyMapper {

  /** 회원정보 수정 **/
  int updateUser(@Param("userVo") UserVo user);

}
