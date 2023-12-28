package com.portfolio.sellf.global.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portfolio.sellf.domain.user.join.service.JoinService;
import com.portfolio.sellf.global.common.log.service.LogService;
import com.portfolio.sellf.global.common.log.vo.LogVo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class UserScheduling {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    JoinService joinService;

    @Autowired
    LogService logService;

  @Scheduled(cron = "0 * * * * *") //매분마다 스케줄러가 돌도록.
  public void guestUserDeleteScheduling() {
    // LogVo logVo = new LogVo();
    // logVo.setLogType("schedule");
    // logVo.setLogInfo("삭제된 게스트 수 : "+ joinService.deleteGuestInfo()+"명");
    // logVo.setLogIp("0");
    // logService.insertLog(logVo);
    logger.info("==========guestUserDeleteScheduling==========");
  }

  @Scheduled(cron = "0 0 8 * * *") //매일 8시마다 스케줄러가 돌도록.
  public void humanUserDeleteScheduling() {
    LogVo logVo = new LogVo();
    logVo.setLogType("schedule");
    logVo.setLogInfo("휴면처리 된 유저 수 : "+ joinService.humanUserInfo()+"명");
    logService.insertLog(logVo);
    logger.info("==========humanUserDeleteScheduling==========");
  }
}
