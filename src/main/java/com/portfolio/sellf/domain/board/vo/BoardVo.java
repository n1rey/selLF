package com.portfolio.sellf.domain.board.vo;

import java.sql.Date;
import java.sql.Time;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardVo {
  private int boardNo;
  private int userNo;
  private String boardTitle;
  private String boardContent;
  private String boardUserName;
  private String boardDropYn;
  private Date boardCreatedDate;
  private Time boardCreatedTime;
  private Date boardDropDate;
  private Time boardDropTime;
  private String boardHideYn;

  @Override
  public String toString() {
    return "BoardVo{" +
            "boardNo=" + boardNo +
            ", userNo=" + userNo +
            ", boardTitle='" + boardTitle + '\'' +
            ", boardContent='" + boardContent + '\'' +
            ", boardUserName='" + boardUserName + '\'' +
            ", boardDropYn='" + boardDropYn + '\'' +
            ", boardCreatedDate=" + boardCreatedDate +
            ", boardCreatedTime=" + boardCreatedTime +
            ", boardDropDate=" + boardDropDate +
            ", boardDropTime=" + boardDropTime +
            ", boardHideYn=" + boardHideYn +
            '}';
  }
}
