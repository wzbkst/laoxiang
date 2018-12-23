package com.qm.laoxiang.dao.mapper;

import com.qm.laoxiang.domain.db.RegionHometownLink;
import com.qm.laoxiang.domain.db.RegionHometownLinkExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegionHometownLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    long countByExample(RegionHometownLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int deleteByExample(RegionHometownLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int insert(RegionHometownLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int insertSelective(RegionHometownLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    List<RegionHometownLink> selectByExample(RegionHometownLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    RegionHometownLink selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByExampleSelective(@Param("record") RegionHometownLink record, @Param("example") RegionHometownLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByExample(@Param("record") RegionHometownLink record, @Param("example") RegionHometownLinkExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByPrimaryKeySelective(RegionHometownLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table region_hometown_link
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByPrimaryKey(RegionHometownLink record);
}