package com.list.service;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/17 15:52
 */


public interface DlriService {
    void recordIp(String ip, Integer id);

    void recordIp(String ip, String fileName);
}
