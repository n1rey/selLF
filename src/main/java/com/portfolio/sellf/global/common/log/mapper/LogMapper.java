package com.portfolio.sellf.global.common.log.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.global.common.log.vo.LogVo;

@Mapper
@Repository
public interface LogMapper {

  //로그추가
  int insertLog(LogVo log);

  List<LogVo> selectLog(Map<String, Object> map);

  List<LogVo> selectAllLog();
}
