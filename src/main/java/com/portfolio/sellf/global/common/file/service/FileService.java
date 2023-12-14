package com.portfolio.sellf.global.common.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String uploadDir = Paths.get("D:", "test", "upload").toString();
  /** 파일 업로드 **/
  public String uploadImage(MultipartFile image) {
    if(image.isEmpty()) {
      return "";
    }

    String orgFilename = image.getOriginalFilename();                                         // 원본 파일명
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");           // 32자리 랜덤 문자열
    String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);  // 확장자
    String saveFilename = uuid + "." + extension;                                             // 디스크에 저장할 파일명
    String fileFullPath = Paths.get(uploadDir, saveFilename).toString();                      // 디스크에 저장할 파일의 전체 경로

    // uploadDir에 해당되는 디렉터리가 없으면, uploadDir에 포함되는 전체 디렉터리 생성
    File dir = new File(uploadDir);
    if (dir.exists() == false) {
      dir.mkdirs();
    }

    try {
      // 파일 저장 (write to disk)
      File uploadFile = new File(fileFullPath);
      image.transferTo(uploadFile);
      return saveFilename;
    } catch (IOException e) {
      // 예외 처리는 따로 해주는 게 좋습니다.
      throw new RuntimeException(e);
    }
  }

  public byte[] printEditorImage(String filename) {
    String fileFullPath = Paths.get(uploadDir, filename).toString();

    // 파일이 없는 경우 예외 throw
    File uploadedFile = new File(fileFullPath);
    if (uploadedFile.exists() == false) {
      throw new RuntimeException();
    }

    try {
      // 이미지 파일을 byte[]로 변환 후 반환
      byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());
      return imageBytes;

    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}