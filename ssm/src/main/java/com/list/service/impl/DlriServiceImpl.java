package com.list.service.impl;

import com.list.mapper.DlriMapper;
import com.list.pojo.Dlri;
import com.list.service.DlriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public void recordIp(String ip, Integer id) {  // 文件id
        dlriMapper.insert(new Dlri(null, new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()), ip, id));
    }
}
