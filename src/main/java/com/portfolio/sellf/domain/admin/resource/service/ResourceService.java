package com.portfolio.sellf.domain.admin.resource.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.sellf.global.common.CommonUtil;
import com.portfolio.sellf.global.common.log.service.LogService;
import com.portfolio.sellf.global.common.log.vo.LogVo;


@Service
public class ResourceService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  LogService logService;

    /**
	 * 디스크 용량
	 */
  public String[] getDiskSpace(HttpServletRequest request) {
    String[] diskList = new String[2];

    File root = null;
    try {
      root = new File("/");
      diskList[0] = toMB(root.getTotalSpace());
      diskList[1] = toMB(root.getUsableSpace());
    } catch (Exception e) {
      LogVo logVo = new LogVo();
      logVo.setLogInfo("에러 : "+e.getMessage());
      logVo.setLogIp(CommonUtil.getIp(request));
      logVo.setLogUri(request.getRequestURI());
      logVo.setLogType("error");
      logService.insertLog(logVo);
    }
    return diskList;
  }


  public String toMB(long size) {
		return String.valueOf((int) (size / (1024 * 1024)));
	}
}