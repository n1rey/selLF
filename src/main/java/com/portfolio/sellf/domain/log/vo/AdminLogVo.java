package com.portfolio.sellf.domain.log.vo;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminLogVo {
  private int logNo;
  private String logType;
  private String logIp;
  private String logInfo;
  private Date log_date;
  private Time log_time;
  private int logUserNo;
  private int logDropYn;
  private String logUri;

  @Override
  public String toString() {
    return "LogVo{" +
            "logNo=" + logNo +
            ", logType='" + logType + '\'' +
            ", logIp='" + logIp + '\'' +
            ", logInfo='" + logInfo + '\'' +
            ", log_date='" + log_date + '\'' +
            ", log_time='" + log_time + '\'' +
            ", logUserNo='" + logUserNo + '\'' +
            ", logDropYn='" + logDropYn + '\'' +
            ", logURI='" + logUri + '\'' +
            "}";
  }
}
