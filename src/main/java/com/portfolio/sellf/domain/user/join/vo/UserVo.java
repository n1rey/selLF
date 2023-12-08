package com.portfolio.sellf.domain.user.join.vo;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
  private int userNo;
  private String userId;
  private String userPassword;
  private String userName;
  private String userProfileImage;
  private Timestamp userJoinDt;
  private int userDropYn;
  private Timestamp userDropDt;
  private String userEmail;
  private String userRole;

  @Override
  public String toString() {
    return "UserVo{" +
            "userNo=" + userNo +
            ", userId='" + userId + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userName='" + userName + '\'' +
            ", userProfileImage='" + userProfileImage + '\'' +
            ", userJoinDt=" + userJoinDt +
            ", userDropYn=" + userDropYn +
            ", userDropDt=" + userDropDt +
            ", userEmail=" + userEmail +
            ", userRole=" + userRole +
            '}';
  }
}
