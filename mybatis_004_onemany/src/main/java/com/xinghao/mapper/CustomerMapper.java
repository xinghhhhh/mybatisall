package com.xinghao.mapper;

import com.xinghao.pojo.Customer;

import java.util.List;

public interface CustomerMapper {

    //根据客户的id查询客户所有的信息并同时查询该客户下的所有订单
    Customer getById(Integer id);
}
