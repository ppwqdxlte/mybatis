package com.laowang.dao;

import com.laowang.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class EmpCacheTest {

    private static SqlSessionFactory factory;
    static {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        factory = builder.build(inputStream);
    }
    /*
    1、第一次查询没命中，但是随着SQL session的关闭，提交给了二级缓存，第二次相同查询就命中了缓存
    2、select * from XXX 这种全查询，毫无过滤条件，压根儿无法缓存
    * */
    @Test
    public void test01(){
        SqlSession sqlSession = factory.openSession();
        EmpDao mapper = sqlSession.getMapper(EmpDao.class);
        Emp emp = mapper.selectByEmpno(1111);System.out.println(emp);
        sqlSession.close();

        SqlSession sqlSession1 = factory.openSession();
        EmpDao mapper1 = sqlSession1.getMapper(EmpDao.class);
        Emp emp1 = mapper1.selectByEmpno(1111);System.out.println(emp1);
        sqlSession1.close();

        SqlSession sqlSession2 = factory.openSession();
        EmpDao mapper2 = sqlSession2.getMapper(EmpDao.class);
        List<Emp> emps = mapper2.searchAll();
        emps.forEach(System.out::println);
        sqlSession2.close();

        SqlSession sqlSession3 = factory.openSession();
        EmpDao mapper3 = sqlSession3.getMapper(EmpDao.class);
        List<Emp> emps3 = mapper3.searchAll();
        emps3.forEach(System.out::println);
        sqlSession3.close();

    }
    /*没命中*/
    @Test
    public void test02(){
        SqlSession sqlSession = factory.openSession();
        EmpDao mapper = sqlSession.getMapper(EmpDao.class);
        Emp emp = mapper.selectByEmpno(1111);

        SqlSession sqlSession1 = factory.openSession();
        EmpDao mapper1 = sqlSession1.getMapper(EmpDao.class);
        Emp emp1 = mapper1.selectByEmpno(1111);
        sqlSession.close();
        sqlSession1.close();
    }
    /*没命中，1级缓存没东西*/
    @Test
    public void test03(){
        SqlSession sqlSession = factory.openSession();
        EmpDao mapper = sqlSession.getMapper(EmpDao.class);
        Emp emp = mapper.selectByEmpno(1111);
        Emp emp1 = mapper.selectByEmpno(1111);
        sqlSession.close();
    }
    /*没命中*/
    @Test
    public void test04(){


    }




}
