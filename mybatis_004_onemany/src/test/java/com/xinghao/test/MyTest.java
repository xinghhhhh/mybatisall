package com.xinghao.test;

import com.xinghao.mapper.BookMapper;
import com.xinghao.mapper.CustomerMapper;
import com.xinghao.mapper.OrdersMapper;
import com.xinghao.pojo.Book;
import com.xinghao.pojo.Customer;
import com.xinghao.pojo.Orders;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MyTest {

    SqlSession sqlSession;
    BookMapper bookMapper;

    CustomerMapper customerMapper;
    OrdersMapper ordersMapper;

    @Before
    public void openSqlSession() throws IOException{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        bookMapper = sqlSession.getMapper(BookMapper.class);
        customerMapper= sqlSession.getMapper(CustomerMapper.class);
        ordersMapper = sqlSession.getMapper(OrdersMapper.class);
    }

    @After
    public void closeSqlSession(){
        sqlSession.close();
    }

    @Test
    public void testGetAll(){
        List<Book> list = bookMapper.getAll();
        list.forEach(book -> System.out.println(book));
    }

    @Test
    public void testGetById(){
        Customer customer = customerMapper.getById(3);
        System.out.println(customer);
    }

    //多对一的情况
    @Test
    public void testGetByIdOrders(){
        Orders orders = ordersMapper.getById(11);
        System.out.println(orders);
    }
}
