package com.qm.laoxiang.domain.db;

/**
 *
 * This class was generated by MyBatis Generator.
 * This class corresponds to the database table qr_info
 *
 * @mbg.generated do_not_delete_during_merge Sun Jan 27 23:02:56 CST 2019
 */
public class QrInfo {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_info.id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_info.qr_url
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    private String qrUrl;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_info.owner_wx
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    private String ownerWx;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_info.region_hometown_id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    private Long regionHometownId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column qr_info.usr_id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    private Long usrId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_info.id
     *
     * @return the value of qr_info.id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_info.id
     *
     * @param id the value for qr_info.id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_info.qr_url
     *
     * @return the value of qr_info.qr_url
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public String getQrUrl() {
        return qrUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_info.qr_url
     *
     * @param qrUrl the value for qr_info.qr_url
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl == null ? null : qrUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_info.owner_wx
     *
     * @return the value of qr_info.owner_wx
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public String getOwnerWx() {
        return ownerWx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_info.owner_wx
     *
     * @param ownerWx the value for qr_info.owner_wx
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public void setOwnerWx(String ownerWx) {
        this.ownerWx = ownerWx == null ? null : ownerWx.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_info.region_hometown_id
     *
     * @return the value of qr_info.region_hometown_id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public Long getRegionHometownId() {
        return regionHometownId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_info.region_hometown_id
     *
     * @param regionHometownId the value for qr_info.region_hometown_id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public void setRegionHometownId(Long regionHometownId) {
        this.regionHometownId = regionHometownId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column qr_info.usr_id
     *
     * @return the value of qr_info.usr_id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public Long getUsrId() {
        return usrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column qr_info.usr_id
     *
     * @param usrId the value for qr_info.usr_id
     *
     * @mbg.generated Sun Jan 27 23:02:56 CST 2019
     */
    public void setUsrId(Long usrId) {
        this.usrId = usrId;
    }
}