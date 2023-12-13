package com.portfolio.sellf.domain.board.vo;

import java.sql.Timestamp;

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
  private Timestamp boardCreatedDate;
  private Timestamp boardDropDate;
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
            ", boardDropDate=" + boardDropDate +
            ", boardHideYn=" + boardHideYn +
            '}';
  }
}
