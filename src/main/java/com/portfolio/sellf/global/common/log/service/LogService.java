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

  @Transactional
  public int insertLog(LogVo log) {
    return logMapper.insertLog(log);
  }

  @Transactional
  public List<LogVo> selectLog(Map<String, Object> map) {
    return logMapper.selectLog(map);
  }

  @Transactional
  public List<LogVo> selectAllLog() {
    return logMapper.selectAllLog();
  }

}
