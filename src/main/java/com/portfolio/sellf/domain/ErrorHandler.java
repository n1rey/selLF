package com.portfolio.sellf.domain;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandler implements ErrorController {

    /**
   * <pre>
   * 에러페이지
   *
   * @author 한승현
   * @date 2023/12/21
   **/
  @RequestMapping(value = "/error") 
  public String handleError(HttpServletRequest request) {
    return "/error";
  }
}
