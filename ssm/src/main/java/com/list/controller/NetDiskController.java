package com.list.controller;

import com.github.pagehelper.PageInfo;
import com.list.pojo.NetDisk;
import com.list.service.DlriService;
import com.list.service.NetDiskService;
import com.list.utils.Result;
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

    @Autowired
    private DlriService dlriService;

    @RequestMapping("/netDisk")
    public String toNetDisk() {
        return "netdisk";
    }

    @RequestMapping("/download")
    public ResponseEntity<byte[]> downLoad(HttpServletRequest request, @RequestParam(value = "id", required = false, defaultValue = "1") Integer id) throws IOException {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        dlriService.recordIp(ip, id);
        NetDisk file = netDiskService.selectOne(id);
        InputStream is = Files.newInputStream(Paths.get(file.getFilePath()));
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
    public Result<String> deleteFile(@RequestParam(value = "id") Integer id) {
        netDiskService.deleteFile(id);
        return Result.ok("已删除");
    }

    @RequestMapping("/fileUpload")
    public String upLoad(@RequestParam(value = "desc", required = false, defaultValue = "没有描述") String desc,
                         @RequestParam(value = "file") MultipartFile[] multipartFiles,
                         HttpServletRequest request) throws IOException {
        for (MultipartFile multipartFile : multipartFiles) {
            // 限定文件大小
            long size = multipartFile.getSize();
            if (size >= 1024 * 1024 * 500) {
                continue;
            }
            // 编号 不重复名字 名字 描述 类型 大小 地址 上传时间 下载次数
            String originalFilename = multipartFile.getOriginalFilename();
            // 处理原始文件名 logo.jpg
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uuid = UUID.randomUUID().toString();
            String newFileName = uuid.replace("-", "").concat(suffix);
            ServletContext servletContext = request.getServletContext();
            String realPath = servletContext.getRealPath(File.separator);
            File file = new File(realPath);
            String parent = file.getParentFile().getParentFile().getParent() + File.separator + "upload";
            // 创建目标存储位置
            File upload = new File(parent);
            String filePath1 = realPath + File.separator + "upload";
            File file1 = new File(filePath1);
            if (!upload.exists()) {
                upload.mkdirs();
            }
            if (!file1.exists()) {
                file1.mkdirs();
            }
            String filePath = parent + File.separator + newFileName;
            File file2 = new File(filePath);
            File file3 = new File(filePath1 + File.separator + newFileName);
            // 向目标位置存储文件
            multipartFile.transferTo(file2);
            Files.copy(file2.toPath(), file3.toPath());
            netDiskService.uploadFile(newFileName,
                    originalFilename,
                    desc,
                    multipartFile.getContentType(),
                    (int) size,
                    filePath,
                    new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        }
        return "redirect:/netDisk";
    }

    @RequestMapping("/netDisk/page")
    @ResponseBody
    public Result<PageInfo<NetDisk>> getAllPath(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum) {
        return Result.ok(netDiskService.selectAllFiles(pageNum));
    }

    @RequestMapping("/netDisk/play")
    public String play(@RequestParam("fileName")String fileName, HttpServletRequest request) {
        request.setAttribute("filePath", "upload\\" + fileName);
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        dlriService.recordIp(ip, fileName);
        return "play";
    }


}
