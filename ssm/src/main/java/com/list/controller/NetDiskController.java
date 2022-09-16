package com.list.controller;

import com.github.pagehelper.PageInfo;
import com.list.pojo.NetDisk;
import com.list.service.NetDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/15 16:05
 */

@Controller
public class NetDiskController {

    @Autowired
    private NetDiskService netDiskService;

    @RequestMapping("/netDisk")
    public String toNetDisk() {
        return "netdisk";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> downLoad(HttpSession httpSession, @RequestParam(value = "id", required = false) Integer id) throws IOException {
        ServletContext servletContext = httpSession.getServletContext();
        NetDisk file = netDiskService.selectOne(id);
        String realPath = servletContext.getRealPath(File.separator + "upload" + File.separator + file.getUuid());
        InputStream is = Files.newInputStream(Paths.get(realPath));
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=" + file.getFileName());
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        is.close();
        return responseEntity;
    }

    @RequestMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam(value = "id", required = false) Integer id) {
        netDiskService.deleteFile(id);
        return "已删除";
    }

    @RequestMapping("/fileUpload")
    public String upLoad(@RequestParam(value = "desc", required = false) String desc ,
                         @RequestParam(value = "file", required = false) MultipartFile[] multipartFiles,
                         HttpServletRequest request) throws IOException {
        int result = 0;
        for (MultipartFile multipartFile : multipartFiles) {
            // 限定文件大小
            long size = multipartFile.getSize();
            if (size>=1024*1024*500) {
                continue;
            }
            // 编号 不重复名字 名字 描述 类型 大小 地址 上传时间 下载次数

            String originalFilename = multipartFile.getOriginalFilename();
            // 处理原始文件名 logo.jpg
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid.replace("-", "").concat(suffix);
            ServletContext servletContext = request.getServletContext();
            String realPath = servletContext.getRealPath(File.separator + "upload");

            // 创建目标存储位置
            File upload=new File(realPath);
            if (!upload.exists()){
                upload.mkdirs();
            }
            String filePath = realPath + File.separator + newFileName;
            // 向目标位置存储文件
            multipartFile.transferTo(new File(filePath));

            result = netDiskService.uploadFile(newFileName,
                                               originalFilename,
                                               desc,
                                               multipartFile.getContentType(),
                                               (int)size,
                                               filePath,
                                               new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        }
        return "redirect:/netDisk";
    }

    @RequestMapping("/netDisk/page")
    @ResponseBody
    public PageInfo<NetDisk> getAllPath(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        PageInfo<NetDisk> netDiskPageInfo = netDiskService.selectAllFiles(pageNum);
        return netDiskPageInfo;
    }


}
