package com.portfolio.sellf.domain.admin.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Mapper
@Repository
public interface UserMapper {

  // 회원 조회
  UserVo selectUser(int userNo);

  List<UserVo> selectAllUser();

  // 회원 숨김
  int hideUser(@Param("userVo") UserVo user);
}
