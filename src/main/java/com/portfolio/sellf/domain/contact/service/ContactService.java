package com.portfolio.sellf.domain.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.sellf.domain.contact.mapper.ContactMapper;

@Service
public class ContactService {

  @Autowired
  private ContactMapper contactMapper;

  /** 회원가입 **/
  @Transactional
  public int test() {
    int userNo = contactMapper.test();
    return userNo;
  }
}