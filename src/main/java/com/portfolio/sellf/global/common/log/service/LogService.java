package com.portfolio.sellf.global.common.log.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.global.common.log.mapper.LogMapper;
import com.portfolio.sellf.global.common.log.vo.LogVo;

@Service
public class LogService {
  
  @Autowired
  private LogMapper logMapper;

  /** 로그 등록**/
  @Transactional
  public int insertLog(LogVo log) {
    return logMapper.insertLog(log);
  }
  /** 로그 조회**/
  @Transactional
  public List<LogVo> selectLog(Map<String, Object> map) {
    return logMapper.selectLog(map);
  }

  /** 모든 로그 조회**/
  @Transactional
  public List<LogVo> selectAllLog() {
    return logMapper.selectAllLog();
  }
}
