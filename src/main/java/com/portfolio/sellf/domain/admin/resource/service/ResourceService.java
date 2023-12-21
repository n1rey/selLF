package com.portfolio.sellf.domain.admin.resource.service;

import java.io.File;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.sellf.global.common.log.service.LogService;
import com.portfolio.sellf.global.common.log.vo.LogVo;
import com.portfolio.sellf.global.common.util.CommonUtil;

@Service
public class ResourceService {

  @Autowired
  LogService logService;

  /** 디스크 용량 **/
  public String[] getDiskSpace(HttpServletRequest request) {
    String[] diskList = new String[3];

    File root = null;
    try {
      root = new File("/");
      diskList[0] = toMB(root.getTotalSpace());
      diskList[1] = toMB(root.getUsableSpace());
      diskList[2] = String.valueOf(root.getUsableSpace()*100/root.getTotalSpace());
    }catch(Exception e) {
      LogVo logVo = new LogVo();
      logVo.setLogInfo("에러 : "+e.getMessage());
      logVo.setLogIp(CommonUtil.getIp(request));
      logVo.setLogUri(request.getRequestURI());
      logVo.setLogType("error");
      logService.insertLog(logVo);
    }
    return diskList;
  }

  /** cpu 용량 **/
  public String[] getCPUProcess(HttpServletRequest request) {
    String[] cpuList = new String[2];
    OperatingSystemMXBean osbean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    String cpuUsage = String.format("%.2f", osbean.getSystemCpuLoad() * 100);

    cpuList[0] = cpuUsage;
    return cpuList;
  }

  /** 메모리 용량 **/
  public String[] getMemory() {
    String[] memList = new String[3];
    MemoryMXBean membean = (MemoryMXBean) ManagementFactory.getMemoryMXBean();
    MemoryUsage heap = membean.getHeapMemoryUsage();
    // MemoryUsage nonheap = membean.getNonHeapMemoryUsage();

    // long heapInit = heap.getInit(); //초기 메모리
    long heapUsed = heap.getUsed(); //사용 메모리
    // long heapCommit = heap.getCommitted(); //JVM할당 메모리
    long heapMax = heap.getMax(); //총 메모리
    double useMemPct = heapUsed * 100 / heap.getMax(); //메모리 사용량

    memList[0] = String.valueOf(useMemPct);
    memList[1] = String.valueOf(heapUsed);
    memList[2] = String.valueOf(heapMax);
    return memList;
  }

  /** 용량변환 **/
  public String toMB(long size) {
    return String.valueOf((int) (size / (1024 * 1024)));
  }
}