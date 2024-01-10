package com.portfolio.sellf.domain.log.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.sellf.domain.log.vo.AdminLogVo;
import com.portfolio.sellf.domain.log.mapper.AdminLogMapper;

@Service
public class AdminLogService {

  @Autowired
  private AdminLogMapper logMapper;

  /** 로그 조회 **/
  public AdminLogVo selectLog(int logNo) {
    return logMapper.selectLog(logNo);
  }

  /** 로그 목록 조회 **/
  public List<AdminLogVo> selectAllLog() {
    return logMapper.selectAllLog();
  }

}