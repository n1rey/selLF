package com.portfolio.sellf.global.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.portfolio.sellf.domain.user.join.vo.UserVo;

@Component
public class CommonUtil {

  public static String getIp(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null) {
      ip = request.getHeader("Proxy-Client-IP");
    }
    if (ip == null) {
      ip = request.getHeader("WL-Proxy-Client-IP");
    }
    if (ip == null) {
      ip = request.getHeader("HTTP_CLIENT_IP");
    }
    if (ip == null) {
      ip = request.getHeader("HTTP_X_FORWARDED_FOR");
    }
    if (ip == null) {
      ip = request.getRemoteAddr();
    }
    return ip;
  }

  public static boolean checkNull(String param) {
    return param != null && !"".equals(param);
  }

  public static UserVo getSessionUser(HttpServletRequest request) {
    return (UserVo) request.getSession().getAttribute("user");
  }
}
