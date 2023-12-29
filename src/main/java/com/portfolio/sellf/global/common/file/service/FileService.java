package com.portfolio.sellf.global.common.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.UUID;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;

@Service
public class FileService {

  @Value("${uploadDir}")
  private String uploadDir;

  @Value("${resizeDir}")
  private String resizeDir;
  
  // private final String uploadDir = Paths.get("D:", "test", "upload").toString();
  // private final String resizeDir = Paths.get("D:", "test", "resize").toString();


  /** 파일 업로드 **/
  public String uploadImage(MultipartFile image) {
    if(image.isEmpty()) {
      return "";
    }
    final MultipartFile resizeImage = image;
    String orgFilename = image.getOriginalFilename();                                         // 원본 파일명
    String uuid = UUID.randomUUID().toString().replaceAll("-", "");           // 32자리 랜덤 문자열
    String extension = orgFilename.substring(orgFilename.lastIndexOf(".") + 1);  // 확장자
    String saveFilename = uuid + "." + extension;                                             // 디스크에 저장할 파일명
    String fileFullPath = Paths.get(uploadDir, saveFilename).toString();                      // 디스크에 저장할 파일의 전체 경로
    String fileResizeFullPath = Paths.get(resizeDir, "resize_"+saveFilename).toString();                      // 디스크에 저장할 파일의 전체 경로

    // uploadDir에 해당되는 디렉터리가 없으면, uploadDir에 포함되는 전체 디렉터리 생성
    File dir = new File(uploadDir);
    File reDir = new File(resizeDir);
    if (dir.exists() == false) {
      dir.mkdirs();
    }
    if (reDir.exists() == false) {
      reDir.mkdirs();
    }

    try {
      // 파일 저장 (write to disk)
      // resize
      File resizeFile = new File(fileResizeFullPath);
      BufferedImage thumbnail = Thumbnails.of(resizeImage.getInputStream()).size(600, 500).asBufferedImage();
      ImageIO.write(thumbnail, extension, resizeFile);

      File uploadFile = new File(fileFullPath);
      image.transferTo(uploadFile);

      return saveFilename;

    }catch(IOException e) {
      // 예외 처리는 따로 해주는 게 좋습니다.
      throw new RuntimeException(e);
    }
  }

  /** 원본 이미지 **/
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

    }catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
  /** 리사이즈 이미지 **/
  public byte[] printResizeImage(String filename) {
    String fileFullPath = Paths.get(resizeDir, "resize_"+filename).toString();

    // 파일이 없는 경우 예외 throw
    File uploadedFile = new File(fileFullPath);

    if (uploadedFile.exists() == false) {
      throw new RuntimeException();
    }

    try {
      // 이미지 파일을 byte[]로 변환 후 반환
      byte[] imageBytes = Files.readAllBytes(uploadedFile.toPath());
      return imageBytes;

    }catch(IOException e) {
      throw new RuntimeException(e);
    }
  }
}