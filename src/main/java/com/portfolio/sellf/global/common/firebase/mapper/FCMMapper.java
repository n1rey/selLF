package com.portfolio.sellf.global.common.firebase.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.global.common.firebase.vo.TokenVo;

@Mapper
@Repository
public interface FCMMapper {

  /** 토큰 등록 **/
  int insertToken(@Param("tokenVo") TokenVo tokenVo);

  /** 토큰을 가지고있는 유저리스트 **/
  List<String> selectUserList(Map<String, Object> map);

  /** 유저로 토큰리스트 검색 **/
  List<String> selectUserTokenList(Map<String, Object> map);

  /** 토큰 여부 체크 **/
  List<TokenVo> checkToken(Map<String, Object> map);

  /** 토큰 삭제 **/
  void deleteToken(@Param("token") String token);

  /** 토큰 유저정보 업데이트 **/
  void updateToken(@Param("tokenVo") TokenVo tokenVo);
}
