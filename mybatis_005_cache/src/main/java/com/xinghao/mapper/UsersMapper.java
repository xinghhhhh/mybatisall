package com.xinghao.mapper;

//数据访问层的接口，规定数据库中可进行的各种操作

import com.xinghao.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    //查询全部用户信息
    List<Users> getAll();

    Users getById(Integer id);


    int update(Users users);

    List<Users> getByName(String name);

    List<Users> getByName2(String name);
    int insert(Users users);

    int delete(Integer id);

    List<Users> getByNameOrAddress(
            @Param("columnName")
            String columnName,
            @Param("columnValue")
            String columnValue
    );

    int insert2(Users users);
}
