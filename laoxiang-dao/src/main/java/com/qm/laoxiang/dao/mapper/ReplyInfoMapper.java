package com.qm.laoxiang.dao.mapper;

import com.qm.laoxiang.domain.db.ReplyInfo;
import com.qm.laoxiang.domain.db.ReplyInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReplyInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    long countByExample(ReplyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int deleteByExample(ReplyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int insert(ReplyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int insertSelective(ReplyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    List<ReplyInfo> selectByExample(ReplyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    ReplyInfo selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByExampleSelective(@Param("record") ReplyInfo record, @Param("example") ReplyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByExample(@Param("record") ReplyInfo record, @Param("example") ReplyInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByPrimaryKeySelective(ReplyInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table reply_info
     *
     * @mbg.generated Sun Dec 23 19:33:53 CST 2018
     */
    int updateByPrimaryKey(ReplyInfo record);
}