package com.portfolio.sellf.global.security.mapper;

import com.portfolio.sellf.global.security.vo.SecurityUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SecurityMapper {

  public SecurityUserVo selectOneUserByUserId(@Param("userId") String userId);

  public SecurityUserVo selectOneUserByUserNo(@Param("userNo") String userNo);
}
