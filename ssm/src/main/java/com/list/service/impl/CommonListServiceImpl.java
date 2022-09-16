package com.list.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.list.constants.ListConstants;
import com.list.mapper.CommonListMapper;
import com.list.mapper.DailyListContentMapper;
import com.list.mapper.DailyListMapper;
import com.list.pojo.CommonList;
import com.list.pojo.CommonListExample;
import com.list.pojo.DailyListContent;
import com.list.pojo.DailyListContentExample;
import com.list.service.CommonListService;
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
 * @create 2022/9/7 18:59
 */

@Service
@Transactional
public class CommonListServiceImpl implements CommonListService {
    @Autowired
    private CommonListMapper commonListMapper;

    @Autowired
    private DailyListContentMapper dailyListContentMapper;

    @Autowired
    private DailyListMapper dailyListMapper;

    @Override
    public PageInfo<CommonList> selectAll(int pageNum) {
        PageHelper.startPage(pageNum, ListConstants.PAGE_SIZE);
        CommonListExample commonListExample = new CommonListExample();
        commonListExample.setOrderByClause("status ASC, create_date DESC, id ASC");
        List<CommonList> commonLists = commonListMapper.selectByExample(commonListExample);
        return new PageInfo<>(commonLists, ListConstants.NAVIGATE_PAGES);
    }

    @Override
    public int deleteCommonListById(Integer id) {
        return commonListMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void addCommonListWithContent(String content) {
        commonListMapper.insert(new CommonList(null, content, new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()), "未完成", 0));
    }

    @Override
    public void finishCommonList(Integer id) {
        CommonList commonList = new CommonList();
        commonList.setId(id);
        commonList.setFinishDate(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        commonList.setStatus(1);
        commonListMapper.updateByPrimaryKeySelective(commonList);
    }

    @Override
    public PageInfo<DailyListContent> selectAllDailyListContent(int dailyPageNum) {
        DailyListContentExample oldDailyListContentExample = new DailyListContentExample();
        oldDailyListContentExample.setOrderByClause("create_date DESC");
        List<DailyListContent> oldDailyListContents = dailyListContentMapper.selectByExample(oldDailyListContentExample);
        if (oldDailyListContents.size() == 0) {
            PageHelper.startPage(dailyPageNum, ListConstants.PAGE_SIZE);
            List<DailyListContent> dailyListContents = dailyListContentMapper.selectByExample(null);
            return new PageInfo<>(dailyListContents, ListConstants.NAVIGATE_PAGES);
        }
        DailyListContent dailyListContent = oldDailyListContents.get(0);
        String createDate = dailyListContent.getCreateDate();
        String substring = createDate.substring(0, 11);
        String date = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());

        if (!date.equals(substring)) {
            DailyListContentExample newDLCE = new DailyListContentExample();
            DailyListContentExample.Criteria criteria = newDLCE.createCriteria();
            criteria.andCreateDateLike(substring + "%");
            List<DailyListContent> newDailyListContents = dailyListContentMapper.selectByExample(newDLCE);
            newDailyListContents.forEach(o -> {
                o.setStatus(0);
                o.setFinishDate("未完成");
                o.setId(null);
                o.setCreateDate(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
                dailyListContentMapper.insertSelective(o);
            });

        }
        DailyListContentExample dailyListContentExample = new DailyListContentExample();
        dailyListContentExample.createCriteria().andCreateDateLike(date + "%");
        dailyListContentExample.setOrderByClause("status ASC, create_date DESC, id ASC");
        PageHelper.startPage(dailyPageNum, ListConstants.PAGE_SIZE);
        List<DailyListContent> dailyListContents = dailyListContentMapper.selectByExample(dailyListContentExample);
        return new PageInfo<>(dailyListContents, ListConstants.NAVIGATE_PAGES);

    }

    @Override
    public void deleteDailyListById(Integer id) {
        dailyListContentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void finishDailyListContent(Integer id) {
        DailyListContent dailyListContent = new DailyListContent();
        dailyListContent.setId(id);
        dailyListContent.setFinishDate(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()));
        dailyListContent.setStatus(1);
        dailyListContentMapper.updateByPrimaryKeySelective(dailyListContent);
    }

    @Override
    public void addDailyListContent(String content) {
        dailyListContentMapper.insert(new DailyListContent(null, null, content, new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss").format(new Date()), "未完成", 0));
    }

}
