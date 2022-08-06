package com.xinghao.mapper;

//数据访问层的接口，规定数据库中可进行的各种操作

import com.xinghao.pojo.Users;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    //按指定条件进行多条件查询
    List<Users> getByCondition(Users users);

    //有选择的更新
    int updateBySet(Users users);

    //查询多个指定id的用户
    List<Users> getByIds(Integer []arr);

    //批量删除
    int deleteBatch(Integer []arr);

    //批量增加
    int insertBatch(List<Users> list);

    //查询指定范围内的用户
    List<Users> getByBirthday(Date begin, Date end);

    //入参是map（重点），实体类无法封装所有条件
    List<Users> getByMap(Map map);

    //返回值是map，一行的情况
    Map getReturnMap(Integer id);

    //返回值是map，多行的情况
    List<Map> getMulMap();
}
