package com.portfolio.sellf.domain.log.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.portfolio.sellf.domain.log.vo.AdminLogVo;

@Mapper
@Repository
public interface AdminLogMapper {

  /** 게시글 조회 **/
  AdminLogVo selectLog(int logNo);

  List<AdminLogVo> selectAllLog();
}
