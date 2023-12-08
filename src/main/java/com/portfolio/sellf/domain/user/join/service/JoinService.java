package com.portfolio.sellf.domain.user.join.service;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.mapper.JoinMapper;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommandMap;
import com.portfolio.sellf.global.common.CommonUtil;
import com.portfolio.sellf.global.common.Encryption;
import com.portfolio.sellf.global.common.RandomInfo;
import com.portfolio.sellf.global.common.log.mapper.LogMapper;
import com.portfolio.sellf.global.common.log.vo.LogVo;

@Service
public class JoinService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private JoinMapper joinMapper;

  @Autowired
  private LogMapper logMapper;

  /** 회원가입 **/
  @Transactional
  public int joinUser(UserVo user) {
    String encryptPassword = Encryption.encodeSha(user.getUserPassword());
    user.setUserPassword(encryptPassword);

    //유저ID가 겹치는지 확인
    CommandMap map = new CommandMap();
    map.put("userid", user.getUserId());
    if(checkId(map) > 0) {
      return -999;
    }
    
    int userNo = joinMapper.joinUser(user);

    return userNo;
  }

  /**아이디 중복체크**/
  public int checkId(CommandMap map) {
    return joinMapper.checkId(map.getMap());
  }

  public CommandMap beforeIssueUser(CommandMap map, HttpServletRequest request) {
    LogVo logVo = new LogVo();
    String type = "issue";
    String ip = CommonUtil.getIp(request);
    String uri = "/join/issue.do";

    if(!map.get("key").equals("good")) { //비정상접근
      logVo.setLogInfo("키 누락, 비정상적인 접근");
      logVo.setLogIp(ip);
      logVo.setLogURI(uri);
      logVo.setLogType("caution");
      logMapper.insertLog(logVo);

      map.put("result", "caution");
      map.put("message", "비정상 접근이 감지되었습니다.");

      return map;
    }else{
      map.put("LOG_TYPE", type);
      map.put("LOG_IP", ip);
      map.put("LOG_URI", uri);
      map.put("TIME","1");
      List<LogVo> logList = logMapper.selectLog(map.getMap());
      if(logList.size() > 0) {
        map.put("result", "fail");
        map.put("message", "이미 발급받은 계정이 존재합니다.");
        return map;
      }
      logVo.setLogType(type);
      logVo.setLogIp(ip);
      logVo.setLogInfo("일회용 계정 발급");
      logVo.setLogURI(uri);
      logMapper.insertLog(logVo);
      map.put("result", "success");
      return map;
    }
  }

  public CommandMap afterIssueUser() {
    CommandMap map = new CommandMap();
    UserVo userVo = createRandomUser();
    String password = RandomInfo.randomPassword();
    userVo.setUserPassword(Encryption.encodeSha(password));
    joinMapper.joinUser(userVo);
    
    map.put("userId", userVo.getUserId());
    map.put("password", password);
    return map;
  }

  public UserVo createRandomUser() {
    UserVo userVo = new UserVo();
    String id = "sellf"+RandomInfo.randomId(10);
    userVo.setUserId(id);
    userVo.setUserName("게스트");
    userVo.setUserRole("guest");

    return userVo;
  }
}