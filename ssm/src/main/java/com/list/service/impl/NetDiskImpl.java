package com.list.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.list.constants.ListConstants;
import com.list.mapper.NetDiskMapper;
import com.list.pojo.NetDisk;
import com.list.service.NetDiskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/16 13:08
 */

@Service
@Transactional
public class NetDiskImpl implements NetDiskService {

    @Autowired
    private NetDiskMapper netDiskMapper;

    @Override
    public int uploadFile(String uuid, String fileName, String desc, String fileType, Integer size, String filePath, String uploadDate) {
        return netDiskMapper.insert(new NetDisk(null, uuid, fileName, desc, fileType, size, filePath, uploadDate, 0));
    }

    @Override
    public PageInfo<NetDisk> selectAllFiles(Integer pageNum) {
        PageHelper.startPage(pageNum, ListConstants.PAGE_SIZE);
        List<NetDisk> netDisks = netDiskMapper.selectByExample(null);
        return new PageInfo<>(netDisks, ListConstants.NAVIGATE_PAGES);
    }

    @Override
    public void deleteFile(Integer id) {

        //表面删除

        netDiskMapper.deleteByPrimaryKey(id);
    }

    @Override
    public NetDisk selectOne(Integer id) {
        NetDisk netDisk = netDiskMapper.selectByPrimaryKey(id);
        netDisk.setDownloadCount(netDisk.getDownloadCount() + 1);
        netDiskMapper.updateByPrimaryKeySelective(netDisk);
        return netDisk;
    }



}
