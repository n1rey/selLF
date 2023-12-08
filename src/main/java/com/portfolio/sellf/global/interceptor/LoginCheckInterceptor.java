package com.portfolio.sellf.global.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.CommonUtil;


public class LoginCheckInterceptor implements HandlerInterceptor{

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    logger.info("==========================preInterceptor============================");
    HttpSession session = request.getSession();
    UserVo user = (UserVo) session.getAttribute("user");
    logger.info(request.getRequestURI()+"============================");
    logger.info(user + " ");
    if(request.getRequestURI().equals("/login") && user != null) {
      String ip = CommonUtil.getIp(request);
      logger.info("비정상 접근 시도 사용자 : "+user.getUserId()+" IP : "+ ip);
      //이 부분에서 로그와 DB로그테이블에 저장하면 될듯.
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
