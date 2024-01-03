package com.portfolio.sellf.global.common.firebase.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.global.common.firebase.vo.TokenVo;

@Mapper
@Repository
public interface FCMMapper {

  /** 토큰 등록 **/
  int insertToken(@Param("tokenVo") TokenVo tokenVo);

  List<String> selectTokenList();
}
