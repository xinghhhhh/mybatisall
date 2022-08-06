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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

public class MyTest {
    SqlSession sqlSession;
    UsersMapper uMapper;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
    @Before
    public void openSqlSession() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession = factory.openSession();
        //取出动态代理的对象，完成接口中方法的调用，实则是调用xml文件中相应的标签的功能
        uMapper = sqlSession.getMapper(UsersMapper.class);
    }

    @After
    public void closeSqlSession(){
        sqlSession.close();
    }

    @Test
    public void testGetAll(){
        List<Users> list = uMapper.getAll();
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testGetById(){
        Users users = uMapper.getById(1);
        System.out.println(users);
    }

    @Test
    public void testUpdate() throws ParseException {
        int result = uMapper.update(new Users(1,"xinghao",sf.parse("2021-01-05"),"1","shanghai"));
        System.out.println(result);
        sqlSession.commit();
    }

    @Test
    public void testGetByName(){
        List<Users> list = uMapper.getByName("张");
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testInsert() throws ParseException {
        int result = uMapper.insert(new Users("zhangmazi",sf.parse("2022-05-05"),"2","beijing"));
        System.out.println(result);
        sqlSession.commit();
    }

    @Test
    public void delete(){
        int result = uMapper.delete(7);
        System.out.println(result);
        sqlSession.commit();
    }
    @Test
    public void testGetByName2(){
        List<Users> list = uMapper.getByName2("张");
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testGetByNameOrAddress(){
        //List<Users> list =  uMapper.getByNameOrAddress("username","小");
        List<Users> list =  uMapper.getByNameOrAddress("address","市");
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testInsert2() throws ParseException {
        Users users= new Users("mazi",sf.parse("2022-05-05"),"2","beijing");
        int result = uMapper.insert2(users);
        System.out.println(result);
        sqlSession.commit();
        System.out.println(users.getId());
    }

    @Test
    public void testUUID(){
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(uuid.toString().replace("-","").substring(20));
    }


    @Test
    public void testCache(){
        //第一次取id=5的用户
        Users u1 = uMapper.getById(5);
        System.out.println(u1);
        System.out.println("********************");
        Users u2 = uMapper.getById(5);
        System.out.println(u2);

        System.out.println(u1==u2);
    }

}