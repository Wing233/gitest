package com.list.controller;

import com.github.pagehelper.PageInfo;
import com.list.pojo.CommonList;
import com.list.pojo.DailyListContent;
import com.list.service.CommonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by IntelliJ IDEA
 *
 * @Author : C22
 * @create 2022/9/7 18:59
 */

@Controller
public class CommonListController {
    @Autowired
    private CommonListService commonListService;


    @RequestMapping(value = "/commonlists")
    public String toList() {
        return "common_list";
    }

    @RequestMapping(value = "/commonlists/page")
    @ResponseBody
    public List<Object> getAllCommonList(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "dailyPageNum", required = false, defaultValue = "1") Integer dailyPageNum) {
        PageInfo<CommonList> commonLists = commonListService.selectAll(pageNum);
        PageInfo<DailyListContent> dailyListContents = commonListService.selectAllDailyListContent(dailyPageNum);
        List<Object> list = new ArrayList<>();
        list.add(commonLists);
        list.add(dailyListContents);
        return list;
    }

    @RequestMapping(value = "/commonlists/delete")
    @ResponseBody
    public String deleteCommonList(@RequestParam(value = "id", required = false) Integer id) {
        commonListService.deleteCommonListById(id);
        return "已删除";
    }

    @RequestMapping(value = "/commonlists/update")
    @ResponseBody
    public String updateCommonList(@RequestParam(value = "id", required = false) Integer id) {
        commonListService.finishCommonList(id);
        return "已完成";
    }

    @PostMapping("/addOrUpdateList")
    @ResponseBody
    public String addCommonList(@RequestParam(value = "addContent", required = false) String content) {
        commonListService.addCommonListWithContent(content);
        return "添加成功";
    }

    @RequestMapping(value = "/commonlists/deleteDaily")
    @ResponseBody
    public String deleteDailyListContent(@RequestParam(value = "id", required = false)Integer id) {
        commonListService.deleteDailyListById(id);
        return "已删除";
    }

    @RequestMapping(value = "/commonlists/updateDaily")
    @ResponseBody
    public String updateDailyListContent(@RequestParam(value = "id", required = false)Integer id) {
        commonListService.finishDailyListContent(id);
        return "已完成";
    }

    @PostMapping("/addOrUpdateDailyList")
    @ResponseBody
    public String addDailyListContent(@RequestParam(value = "addDailyContent", required = false) String content) {
        commonListService.addDailyListContent(content);
        return "已添加";
    }

}
