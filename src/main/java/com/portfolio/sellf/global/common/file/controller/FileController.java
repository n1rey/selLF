package com.portfolio.sellf.global.common.file.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.portfolio.sellf.global.common.file.service.FileService;

@Controller
@RequestMapping("/file")
public class FileController {

  @Autowired
  private FileService fileService;

    /**
   * <pre>
   * 이미지 업로드
   *
   * @author 한승현
   * @date 2023/12/14
   **/
  @ResponseBody
  @RequestMapping(value = {"/upload/image.do"}) 
  public String uploadImage(@RequestParam final MultipartFile image) {
    System.out.println();
    return fileService.uploadImage(image);
  }

    /**
   * <pre>
   * 이미지 불러오기
   *
   * @author 한승현
   * @date 2023/12/14
   **/
  @ResponseBody
  @RequestMapping(value = {"/print/image.do"}) 
  public byte[] printImage(@RequestParam final String filename) {
    // return fileService.printThumbnailImage(filename);
    return fileService.printEditorImage(filename);
  }

    /**
   * <pre>
   * 리사이즈이미지 불러오기
   *
   * @author 한승현
   * @date 2023/12/14
   **/
  @ResponseBody
  @RequestMapping(value = {"/print/resizeImage.do"}) 
  public byte[] printResizeImage(@RequestParam final String filename) {
    return fileService.printResizeImage(filename);
  }
}
