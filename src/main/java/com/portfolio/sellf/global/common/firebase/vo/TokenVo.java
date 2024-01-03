package com.portfolio.sellf.global.common.firebase.vo;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenVo {
  private String userId;
  private String tokenId;
  private Date tokenCreatedDate;
  private Time tokenCreatedTime;

  @Override
  public String toString() {
    return "TokenVo{" +
            ", userId=" + userId +
            ", tokenId='" + tokenId + '\'' +
            ", tokenCreatedDate='" + tokenCreatedDate + '\'' +
            ", tokenCreatedTime=" + tokenCreatedTime +
            '}';
  }
}
