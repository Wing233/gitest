package com.list.controller;

import com.github.pagehelper.PageInfo;
import com.list.pojo.CommonList;
import com.list.pojo.DailyListContent;
import com.list.service.CommonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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

    @RequestMapping(value = "/commonlists/page/{pageNum}/{dailyPageNum}")
    public String getAllCommonList(@PathVariable(value = "pageNum") Integer pageNum,
                                   @PathVariable(value = "dailyPageNum") Integer dailyPageNum,
                                   Model model) {
        PageInfo<CommonList> commonLists = commonListService.selectAll(pageNum);
        PageInfo<DailyListContent> dailyListContents = commonListService.selectAllDailyListContent(dailyPageNum);
        model.addAttribute("commonLists", commonLists);
        model.addAttribute("dailyListContents", dailyListContents);
        return "common_list";
    }

    @RequestMapping(value = "/commonlists/delete/{pageNum}/{id}", method = RequestMethod.DELETE)
    public String deleteCommonList(@PathVariable(value = "pageNum")Integer pageNum,
                                   @PathVariable(value = "id")Integer id) {
        commonListService.deleteCommonListById(id);
        return "redirect:/commonlists/page/" + pageNum + "/1";
    }

    @RequestMapping(value = "/commonlists/update/{id}", method = RequestMethod.PUT)
    public String updateCommonList(@PathVariable(value = "id")Integer id) {
        commonListService.finishCommonList(id);
        return "redirect:/commonlists/page/1/1";
    }

    @PostMapping("/addOrUpdateList")
    public String addCommonList(String content) {
        commonListService.addCommonListWithContent(content);
        return "redirect:/commonlists/page/1/1";
    }

    @RequestMapping(value = "/commonlists/deleteDaily/{pageNum}/{id}", method = RequestMethod.DELETE)
    public String deleteDailyListContent(@PathVariable(value = "pageNum")Integer pageNum,
                                         @PathVariable(value = "id")Integer id) {
        commonListService.deleteDailyListById(id);
        return "redirect:/commonlists/page/1/" + pageNum;
    }

    @RequestMapping(value = "/commonlists/updateDaily/{id}", method = RequestMethod.PUT)
    public String updateDailyListContent(@PathVariable(value = "id")Integer id) {
        commonListService.finishDailyListContent(id);
        return "redirect:/commonlists/page/1/1";
    }

    @PostMapping("/addOrUpdateDailyList")
    public String addDailyListContent(String content) {
        commonListService.addDailyListContent(content);
        return "redirect:/commonlists/page/1/1";
    }

}
