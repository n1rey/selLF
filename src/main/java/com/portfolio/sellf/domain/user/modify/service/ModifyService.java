package com.portfolio.sellf.domain.user.modify.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.domain.user.modify.mapper.ModifyMapper;
import com.portfolio.sellf.global.common.CommandMap;
import com.portfolio.sellf.global.common.CommonUtil;
import com.portfolio.sellf.global.common.Encryption;
import com.portfolio.sellf.global.common.log.service.LogService;
import com.portfolio.sellf.global.common.log.vo.LogVo;

@Service
public class ModifyService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ModifyMapper modifyMapper;

  @Autowired
  private LogService logService;

  /** 비밀번호 체크 **/
  public String checkPassword(HttpServletRequest request, CommandMap map) {
    UserVo user = CommonUtil.getSessionUser(request);
    try {
      if(user.getUserPassword().equals(Encryption.encodeSha(map.get("userPassword").toString()))) return "";
    } catch (Exception e) {
      LogVo logVo = new LogVo();
      logVo.setLogInfo("비정상 접근 시도");
      logVo.setLogIp(CommonUtil.getIp(request));
      logVo.setLogUri(request.getRequestURI());
      logVo.setLogType("caution");
      logService.insertLog(logVo);
      return "비정상적인 접근입니다.";
    }
    return "패스워드가 일치하지않습니다.";
  }

  /** 회원정보수정 **/
  @Transactional
  public int updateUser(UserVo user) {
    if(!CommonUtil.checkNull(user.getUserPassword())){
      String encryptPassword = Encryption.encodeSha(user.getUserPassword());
      user.setUserPassword(encryptPassword);
    }
    int result = modifyMapper.updateUser(user);

    return result;
  }
}