package com.portfolio.sellf.domain.admin.code.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.admin.code.vo.CodeVo;

@Mapper
@Repository
public interface CodeMapper {

  List<CodeVo> selectCodeList();

  String selectCode(String id);

  int insertCode(@Param("codeVo") CodeVo codeVo);

  int updateCode(@Param("codeVo") CodeVo codeVo);

  int deleteCode(@Param("codeVo") CodeVo codeVo);

  int checkCodeId(@Param("codeVo") CodeVo codeVo);
}
