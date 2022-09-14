package com.list.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DailyListContent implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dailylistcontent.id
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dailylistcontent.content_id
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    private Integer contentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dailylistcontent.content
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    private String content;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dailylistcontent.create_date
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    private String createDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dailylistcontent.finish_date
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    private String finishDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dailylistcontent.status
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    private Integer status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dailylistcontent.id
     *
     * @return the value of dailylistcontent.id
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dailylistcontent.id
     *
     * @param id the value for dailylistcontent.id
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dailylistcontent.content_id
     *
     * @return the value of dailylistcontent.content_id
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public Integer getContentId() {
        return contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dailylistcontent.content_id
     *
     * @param contentId the value for dailylistcontent.content_id
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dailylistcontent.content
     *
     * @return the value of dailylistcontent.content
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dailylistcontent.content
     *
     * @param content the value for dailylistcontent.content
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dailylistcontent.create_date
     *
     * @return the value of dailylistcontent.create_date
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dailylistcontent.create_date
     *
     * @param createDate the value for dailylistcontent.create_date
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate == null ? null : createDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dailylistcontent.finish_date
     *
     * @return the value of dailylistcontent.finish_date
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public String getFinishDate() {
        return finishDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dailylistcontent.finish_date
     *
     * @param finishDate the value for dailylistcontent.finish_date
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate == null ? null : finishDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dailylistcontent.status
     *
     * @return the value of dailylistcontent.status
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dailylistcontent.status
     *
     * @param status the value for dailylistcontent.status
     *
     * @mbg.generated Fri Sep 09 13:33:10 CST 2022
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}