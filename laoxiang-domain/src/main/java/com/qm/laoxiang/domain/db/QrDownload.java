package com.qm.laoxiang.domain.db;

import java.util.Date;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table qr_download
 *
 * @mbg.generated do_not_delete_during_merge Sun Dec 23 19:33:53 CST 2018
 */
public class QrDownload {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_download.id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_download.usr_id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    private Long usrId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_download.region_hometown_id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    private Long regionHometownId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_download.op_time
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    private Date opTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_download.id
     *
     * @return the value of qr_download.id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_download.id
     *
     * @param id the value for qr_download.id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_download.usr_id
     *
     * @return the value of qr_download.usr_id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public Long getUsrId() {
        return usrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_download.usr_id
     *
     * @param usrId the value for qr_download.usr_id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_download.region_hometown_id
     *
     * @return the value of qr_download.region_hometown_id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public Long getRegionHometownId() {
        return regionHometownId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_download.region_hometown_id
     *
     * @param regionHometownId the value for qr_download.region_hometown_id
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public void setRegionHometownId(Long regionHometownId) {
        this.regionHometownId = regionHometownId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_download.op_time
     *
     * @return the value of qr_download.op_time
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public Date getOpTime() {
        return opTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_download.op_time
     *
     * @param opTime the value for qr_download.op_time
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}