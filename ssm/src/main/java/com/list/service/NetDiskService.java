package com.list.service;

import com.github.pagehelper.PageInfo;
import com.list.pojo.NetDisk;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/16 13:08
 */


public interface NetDiskService {
    // 编号 不重复名字 名字 描述 类型 大小 地址 上传时间 下载次数
    int uploadFile(String uuid,
                    String fileName,
                    String desc,
                    String fileType,
                    Integer size,
                    String filePath,
                    String uploadDate);

    PageInfo<NetDisk> selectAllFiles(Integer pageNum);

    void deleteFile(Integer id);

    NetDisk selectOne(Integer id);
}
