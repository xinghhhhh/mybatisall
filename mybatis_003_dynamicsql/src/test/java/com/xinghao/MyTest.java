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
import java.util.*;

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
    public void testGetByCondition(){
        Users users1 = new Users();
        users1.setAddress("市");
        users1.setSex("1");
        List<Users> list = uMapper.getByCondition(users1);
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testUpdateBySet(){
        Users users = new Users();
        users.setId(3);
        users.setUserName("tttttt");
        int result = uMapper.updateBySet(users);
        sqlSession.commit();
        System.out.println(result);
    }

    @Test
    public void testGetByIds(){
        Integer []array = {2,4,6};
        List<Users> list = uMapper.getByIds(array);
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testDeleteBatch(){
        Integer []array = {2,4,6};
        int result = uMapper.deleteBatch(array);
        sqlSession.commit();
        System.out.println(result);
    }

    @Test
    public void testUpdateBatch() throws ParseException {
        Users u1= new Users("aa",sf.parse("2022-05-05"),"1","zunyi");
        Users u2= new Users("bb",sf.parse("2022-05-05"),"1","zunyi");
        Users u3= new Users("cc",sf.parse("2022-05-05"),"1","zunyi");
        List<Users> list = new ArrayList<>();
        list.add(u1);
        list.add(u2);
        list.add(u3);
        int result = uMapper.insertBatch(list);
        sqlSession.commit();
        System.out.println(result);
    }

    @Test
    public void testGetByBirthday() throws ParseException {
        Date begin = sf.parse("1999-01-22");
        Date end = sf.parse("2001-03-10");
        List<Users> list = uMapper.getByBirthday(begin,end);
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testGetByMap() throws ParseException {
        Date begin = sf.parse("1999-01-22");
        Date end = sf.parse("2001-03-10");
        Map map = new HashMap();
        map.put("birthdayBegin",begin);
        map.put("birthdayEnd",end);
        List<Users> list = uMapper.getByMap(map);
        list.forEach(users -> System.out.println(users));
    }

    @Test
    public void testGetReturnMap(){
        Map map = uMapper.getReturnMap(1);
        System.out.println(map.get("username"));
        System.out.println(map.get("address"));
    }
    @Test
    public void testgetMulMap(){
        List<Map> list = uMapper.getMulMap();
        list.forEach(map -> System.out.println(map));
    }
}