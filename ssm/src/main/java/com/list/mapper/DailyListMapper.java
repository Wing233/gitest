package com.list.mapper;

import com.list.pojo.DailyList;
import com.list.pojo.DailyListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DailyListMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    long countByExample(DailyListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int deleteByExample(DailyListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int insert(DailyList row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int insertSelective(DailyList row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    List<DailyList> selectByExample(DailyListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    DailyList selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int updateByExampleSelective(@Param("row") DailyList row, @Param("example") DailyListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int updateByExample(@Param("row") DailyList row, @Param("example") DailyListExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int updateByPrimaryKeySelective(DailyList row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dailylist
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    int updateByPrimaryKey(DailyList row);
}