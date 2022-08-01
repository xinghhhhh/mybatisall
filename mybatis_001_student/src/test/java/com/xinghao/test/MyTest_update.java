package com.xinghao.test;

import com.xinghao.pojo.Student;
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


public class MyTest_update {
    SqlSession sqlSession;

    @Before //在所有的@Test方法执行前执行的代码
    public void openSqlSession() throws IOException {
        //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //取出SqlSession的对象
        sqlSession = factory.openSession();
    }

    @After
    public void closeSqlSession(){
        //关闭sqlsession
        sqlSession.close();
    }

    @Test
    public void testAll() throws IOException {
        //完成查询操作
        List<Student> list = sqlSession.selectList("tt.getAll");
        list.forEach(student -> System.out.println(student));
    }

    @Test
    public void testGetById() throws IOException {
        Student student = sqlSession.selectOne("tt.getById",1);
        System.out.println(student);
    }

    @Test
    public void testGetByName() throws IOException{
        List<Student> list = sqlSession.selectList("tt.getByName","张");
        list.forEach(student -> System.out.println(student));
    }

    @Test
    public void testInsert() throws IOException{
        int num = sqlSession.insert("tt.insert",new Student("kkkk","hh@qq.com",34));
        System.out.println(num);
        //在增删改后必须提交事务
        sqlSession.commit();
    }

    @Test
    public void testDelete() throws IOException{
        int num = sqlSession.delete("tt.delete",1);
        System.out.println(num);
        //在增删改后必须提交事务
        sqlSession.commit();
    }

    @Test
    public void testUpadte() throws IOException{
        int num = sqlSession.update("tt.update",new Student(3,"张麻子","zhangmazi@qq.com",34));
        System.out.println(num);
        //在增删改后必须提交事务
        sqlSession.commit();
    }
}
