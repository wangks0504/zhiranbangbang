package org.zhiran.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.zhiran.pojo.Result;
import org.zhiran.utils.AliOssUtil;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {
    @PostMapping("userImgUpload")//用于头像的上传
    public Result<String> userImgUpload(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename();
        String fileName = UUID.randomUUID().toString() + "add" +originalFilename;
        //这里需要借用uuid对文件名称加一个事件戳一次来保证文件名称唯一性不然重复名称的文件容易被覆盖
        //.substring(originalFilename.lastIndexOf("."))
//        multipartFile.transferTo(new File("E:\\Important(E)\\Web\\source\\userImgUplaod\\" + fileName));
      String URL =   AliOssUtil.uploadFile(fileName,multipartFile.getInputStream());
        return Result.success(URL);
    }
}
