package com.xinghao.mapper;

//数据访问层的接口，规定数据库中可进行的各种操作

import com.xinghao.pojo.Users;

import java.util.List;

public interface UsersMapper {
    //查询全部用户信息
    List<Users> getAll();
}
