package com.portfolio.sellf.domain.admin.code.vo;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeVo {
  private int codeNo;
  private int userNo;
  private String codeId;
  private String codeDescription;
  private String codeContent;
  private Date codeCreatedDate;
  private Time codeCreatedTime;
  private String codeUseYn;

  @Override
  public String toString() {
    return "CodeVo{" +
            "codeNo=" + codeNo +
            ", userNo=" + userNo +
            ", codeId='" + codeId + '\'' +
            ", codeDescription='" + codeDescription + '\'' +
            ", codeContent='" + codeContent + '\'' +
            ", codeCreatedDate='" + codeCreatedDate + '\'' +
            ", codeCreatedTime=" + codeCreatedTime +
            ", codeUseYn=" + codeUseYn +
            '}';
  }
}
