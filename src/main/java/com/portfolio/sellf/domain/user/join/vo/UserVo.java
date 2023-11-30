package com.portfolio.sellf.domain.user.join.vo;

import java.sql.Timestamp;

public class UserVo {

  private int userNo;
  private String userId;
  private String userPassword;
  private String userNickname;
  private String userProfileImage;
  private Timestamp userJoinDt;
  private int userDropYn;
  private Timestamp userDropDt;
  private String userEmail;

  @Override
  public String toString() {
    return "UserVo{" +
            "userNo=" + userNo +
            ", userId='" + userId + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userNickname='" + userNickname + '\'' +
            ", userProfileImage='" + userProfileImage + '\'' +
            ", userJoinDt=" + userJoinDt +
            ", userDropYn=" + userDropYn +
            ", userDropDt=" + userDropDt +
            ", userEmail=" + userEmail +
            '}';
  }

  public int getUserNo() {
    return userNo;
  }

  public void setUserNo(int userNo) {
    this.userNo = userNo;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public String getUserNickname() {
    return userNickname;
  }

  public void setUserNickname(String userNickname) {
    this.userNickname = userNickname;
  }

  public String getUserProfileImage() {
    return userProfileImage;
  }

  public void setUserProfileImage(String userProfileImage) {
    this.userProfileImage = userProfileImage;
  }

  public Timestamp getUserJoinDt() {
    return userJoinDt;
  }

  public void setUserJoinDt(Timestamp userJoinDt) {
    this.userJoinDt = userJoinDt;
  }

  public int getUserDropYn() {
    return userDropYn;
  }

  public void setUserDropYn(int userDropYn) {
    this.userDropYn = userDropYn;///
  }

  public Timestamp getUserDropDt() {
    return userDropDt;
  }

  public void setUserDropDt(Timestamp userDropDt) {
    this.userDropDt = userDropDt;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }
}
