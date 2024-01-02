package com.portfolio.sellf.global.common.firebase.vo;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMVo {
  private Long targetUserId;
  private String title;
  private String body;

  @Builder
  public FCMVo(Long targetUserId, String title, String body) {
    this.targetUserId = targetUserId;
    this.title = title;
    this.body = body;
  }
}
