package com.portfolio.sellf.domain.admin.code.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.sellf.domain.admin.code.mapper.CodeMapper;
import com.portfolio.sellf.domain.admin.code.vo.CodeVo;
import com.portfolio.sellf.global.common.log.service.LogService;


@Service
public class CodeService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  LogService logService;

  @Autowired
  CodeMapper codeMapper;

  public List<CodeVo> getCodeList() {
    return codeMapper.selectCode();
  }

  public int insertCode(CodeVo codeVo) {
    return codeMapper.insertCode(codeVo);
  }
}