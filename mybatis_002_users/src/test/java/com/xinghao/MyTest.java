package com.xinghao;

import com.xinghao.mapper.UsersMapper;
import com.xinghao.pojo.Users;
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
    @Before
    public void openSqlSession() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
    }

    @After
    public void closeSqlSession(){
        sqlSession.close();
    }

    @Test
    public void testGetAll(){
        //取出动态代理的对象，完成接口中方法的调用，实则是调用xml文件中相应的标签的功能
        UsersMapper uMapper = sqlSession.getMapper(UsersMapper.class);
        List<Users> list = uMapper.getAll();
        list.forEach(users -> System.out.println(users));
    }
}
