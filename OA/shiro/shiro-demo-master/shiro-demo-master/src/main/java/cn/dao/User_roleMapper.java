package cn.dao;

import cn.pojo.User_role;

public interface User_roleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User_role record);

    int insertSelective(User_role record);

    User_role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User_role record);

    int updateByPrimaryKey(User_role record);
    
    int updateByUserId(User_role record);
}