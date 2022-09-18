package com.list.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dlri implements Serializable {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlri.id
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlri.request_date
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    private String requestDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlri.ip
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    private String ip;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column dlri.file_id
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    private Integer fileId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlri.id
     *
     * @return the value of dlri.id
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlri.id
     *
     * @param id the value for dlri.id
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlri.request_date
     *
     * @return the value of dlri.request_date
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlri.request_date
     *
     * @param requestDate the value for dlri.request_date
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate == null ? null : requestDate.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlri.ip
     *
     * @return the value of dlri.ip
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public String getIp() {
        return ip;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlri.ip
     *
     * @param ip the value for dlri.ip
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column dlri.file_id
     *
     * @return the value of dlri.file_id
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public Integer getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column dlri.file_id
     *
     * @param fileId the value for dlri.file_id
     *
     * @mbg.generated Sun Sep 18 13:43:49 CST 2022
     */
    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}