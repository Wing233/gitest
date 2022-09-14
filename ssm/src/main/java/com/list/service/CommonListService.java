package com.list.service;

import com.github.pagehelper.PageInfo;
import com.list.pojo.CommonList;
import com.list.pojo.DailyListContent;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/7 18:59
 */


public interface CommonListService {

    PageInfo<CommonList> selectAll(int pageNum);

    int deleteCommonListById(Integer id);

    void addCommonListWithContent(String content);

    void finishCommonList(Integer id);

    PageInfo<DailyListContent> selectAllDailyListContent(int dailyPageNum);

    void deleteDailyListById(Integer id);

    void finishDailyListContent(Integer id);

    void addDailyListContent(String content);
}
