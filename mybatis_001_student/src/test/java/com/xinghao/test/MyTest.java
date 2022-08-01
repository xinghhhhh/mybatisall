package com.xinghao.test;

import com.xinghao.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MyTest {

    @Test
    public void testAll() throws IOException {
      //使用文件流读取核心配置文件SqlMapConfig.xml
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
      //创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
      //取出SqlSession的对象
        SqlSession sqlSession = factory.openSession();
      //完成查询操作
        List<Student> list = sqlSession.selectList("tt.getAll");
        list.forEach(student -> System.out.println(student));
      //关闭sqlsession
        sqlSession.close();
    }

    @Test
    public void testGetById() throws IOException {
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        Student student = sqlSession.selectOne("tt.getById",1);
        System.out.println(student);
        sqlSession.close();
    }

    @Test
    public void testGetByName() throws IOException{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        List<Student> list = sqlSession.selectList("tt.getByName","张");
        list.forEach(student -> System.out.println(student));
        sqlSession.close();
    }

    @Test
    public void testInsert() throws IOException{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        int num = sqlSession.insert("tt.insert",new Student("哈哈","hh@qq.com",34));
        System.out.println(num);
        //在增删改后必须提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testDelete() throws IOException{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        int num = sqlSession.delete("tt.delete",1);
        System.out.println(num);
        //在增删改后必须提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpadte() throws IOException{
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession = factory.openSession();
        int num = sqlSession.update("tt.update",new Student(3,"张麻子","gg@qq.com",34));
        System.out.println(num);
        //在增删改后必须提交事务
        sqlSession.commit();
        sqlSession.close();
    }
}
