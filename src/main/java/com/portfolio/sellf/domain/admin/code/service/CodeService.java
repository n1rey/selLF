package com.portfolio.sellf.domain.admin.code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.sellf.domain.admin.code.mapper.CodeMapper;
import com.portfolio.sellf.domain.admin.code.vo.CodeVo;

@Service
public class CodeService {

  @Autowired
  CodeMapper codeMapper;

  /** 코드 조회 **/
  public List<CodeVo> getCodeList() {
    return codeMapper.selectCodeList();
  }

  /** 코드 조회 **/
  public String getCode(String id) {
    return codeMapper.selectCode(id);
  }

  /** 코드 추가 **/
  public int insertCode(CodeVo codeVo) {
    int result = codeMapper.checkCodeId(codeVo);
    if(result > 0) {
      return -999;
    }
    return codeMapper.insertCode(codeVo);
  }

  /** 코드 수정 **/
  public int updateCode(CodeVo codeVo) {
    return codeMapper.updateCode(codeVo);
  }

  /** 코드 삭제 **/
  public int deleteCode(CodeVo codeVo) {
    return codeMapper.deleteCode(codeVo);
  }
}