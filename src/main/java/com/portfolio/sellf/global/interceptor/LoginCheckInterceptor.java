package com.portfolio.sellf.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommonUtil;
import com.portfolio.sellf.global.common.log.service.LogService;
import com.portfolio.sellf.global.common.log.vo.LogVo;


public class LoginCheckInterceptor implements HandlerInterceptor{

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  LogService logService;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.info("==========================preInterceptor============================");
    UserVo user = CommonUtil.getSessionUser(request);
    String referer = request.getHeader("Referer");
    System.out.println(request.getSession().getAttribute("user")+"==============================================");
    System.out.println(request.getSession().getAttribute("role")+"==============================================");

    if(request.getRequestURI().equals("/login") && !CommonUtil.checkNull(referer)) {
      String ip = CommonUtil.getIp(request);
      logger.info("비정상 접근 시도 사용자 : "+user.getUserId()+" IP : "+ ip);
      //이 부분에서 로그와 DB로그테이블에 저장하면 될듯.
      LogVo logVo = new LogVo();
      logVo.setLogInfo("비정상 접근 시도 사용자 : "+user.getUserId());
      logVo.setLogIp(ip);
      logVo.setLogUri(request.getRequestURI());
      logVo.setLogType("caution");
      logService.insertLog(logVo);

      response.sendRedirect(request.getContextPath()+"/");
      return false;
    }else if(request.getRequestURI().startsWith("/modify") && !CommonUtil.checkNull(referer)) {
      String ip = CommonUtil.getIp(request);
      logger.info("비정상 접근 시도 사용자 IP : "+ ip);
      //이 부분에서 로그와 DB로그테이블에 저장하면 될듯.
      LogVo logVo = new LogVo();
      logVo.setLogInfo("비정상 접근 시도");
      logVo.setLogIp(ip);
      logVo.setLogUri(request.getRequestURI());
      logVo.setLogType("caution");
      logService.insertLog(logVo);

      response.sendRedirect(request.getContextPath()+"/");
      return false;
    }
    
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

  }
}
