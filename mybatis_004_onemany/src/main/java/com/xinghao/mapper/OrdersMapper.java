package com.xinghao.mapper;

import com.xinghao.pojo.Orders;

public interface OrdersMapper {
    //根据主键查订单，并同时查客户信息
    Orders getById(Integer id);
}
