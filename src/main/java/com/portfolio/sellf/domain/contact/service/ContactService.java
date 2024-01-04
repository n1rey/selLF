package com.portfolio.sellf.domain.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.sellf.domain.contact.mapper.ContactMapper;
import com.portfolio.sellf.global.common.util.CommandMap;
import com.portfolio.sellf.global.common.util.SendMail;

@Service
public class ContactService {

  @Autowired
  private ContactMapper contactMapper;

  /** 메일 발송 **/
  public void sendContact(CommandMap commandMap) {
    SendMail.sendMail(commandMap, "contact");
  }
}