package com.portfolio.sellf.global.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portfolio.sellf.global.common.log.service.LogService;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.SendMail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableAsync
public class MailScheduling {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LogService logService;

    /**
  * Cron 표현식을 사용한 작업 예약
  * 초(0-59) 분(0-59) 시간(0-23) 일(1-31) 월(1-12) 요일(0-7)
  */
  @Scheduled(cron = "0 0 10 * * *") //매일 오전 10시마다 스케줄러가 돌도록.
  public void mailScheduling() {
    CommandMap map = new CommandMap();
    map.put("LOG_TYPE", "caution");
    map.put("TIME", "24");
    map.put("caution", logService.selectLog(map.getMap()).size());
    map.put("LOG_TYPE", "issue");
    map.put("issue", logService.selectLog(map.getMap()).size());

    SendMail.sendMail(map, "schedule");
    logger.info("==========Mailscheduling==========");
  }
}
