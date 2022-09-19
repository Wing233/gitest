package com.list.service.impl;

import com.list.mapper.DlriMapper;
import com.list.mapper.NetDiskMapper;
import com.list.pojo.Dlri;
import com.list.pojo.NetDisk;
import com.list.pojo.NetDiskExample;
import com.list.service.DlriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/17 15:52
 */

@Service
@Transactional
public class DlriServiceImpl implements DlriService {
    @Autowired
    private DlriMapper dlriMapper;

    @Autowired
    private NetDiskMapper netDiskMapper;

    @Override
    public void recordIp(String ip, Integer id) {  // 文件id
        dlriMapper.insert(new Dlri(null, new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()), ip, id));
    }

    @Override
    public void recordIp(String ip, String fileName) {
        NetDiskExample netDiskExample = new NetDiskExample();
        netDiskExample.createCriteria().andUuidEqualTo(fileName);
        List<NetDisk> netDisks = netDiskMapper.selectByExample(netDiskExample);
        NetDisk netDisk = netDisks.get(0);
        Integer id = netDisk.getId();
        recordIp(ip, id);
    }
}
