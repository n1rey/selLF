package com.portfolio.sellf.domain.admin.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.portfolio.sellf.domain.admin.user.service.UserService;
import com.portfolio.sellf.domain.user.hide.service.HideService;
import com.portfolio.sellf.domain.user.join.vo.UserVo;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.CommonUtil;


@Controller
@RequestMapping("/admin/users")
public class UserController {

  @Autowired
  private UserService userService;

  @Autowired
  private HideService hideService;
  
    /**
   * <pre>
   * 회원 목록 조회
   *
   * @author 변예린
   * @date 2023/01/22
   **/
  @RequestMapping(value = {"", "/"}) 
  public String logPage(HttpServletRequest request, Model model, CommandMap map) {
    model.addAttribute("userList", userService.selectAllUser());
    model.addAttribute("userNo", CommonUtil.getSessionUser(request).getUserNo());
    return "/admin/users";
  }

    /**
   * <pre>
   * 회원 조회
   *
   * @author 변예린
   * @date 2023/01/22
   **/
  @ResponseBody
  @RequestMapping(value = {"/getUser/{userNo}"}) 
  public UserVo usernfo(HttpServletRequest request, Model model, @PathVariable int userNo) {
    UserVo userVo = userService.selectUser(userNo);
    return userVo;
  }

    /**
   * <pre>
   * 회원 숨김
   *
   * @author 변예린
   * @date 2023/01/22
   **/
  @ResponseBody
  @PostMapping("/submit.do") 
  public int submitInfo(UserVo user) {
    int result = hideService.hideUser(user);
    return result;
  }
}
