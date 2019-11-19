package com.briup.apps.cms.dao;

import com.briup.apps.cms.bean.UserAndRole;
import com.briup.apps.cms.bean.UserAndRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAndRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    long countByExample(UserAndRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int deleteByExample(UserAndRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int insert(UserAndRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int insertSelective(UserAndRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    List<UserAndRole> selectByExample(UserAndRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    UserAndRole selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int updateByExampleSelective(@Param("record") UserAndRole record, @Param("example") UserAndRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int updateByExample(@Param("record") UserAndRole record, @Param("example") UserAndRoleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int updateByPrimaryKeySelective(UserAndRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table base_user_role
     *
     * @mbg.generated Thu Nov 14 19:40:36 CST 2019
     */
    int updateByPrimaryKey(UserAndRole record);
}