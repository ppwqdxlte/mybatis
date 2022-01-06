package com.laowang.dao;

import com.laowang.bean.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class EmpDaoTest {

    private String resource = "mybatis-config.xml";
    private SqlSessionFactory factory;
    private SqlSession session;

    private void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(resource);
        factory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @BeforeEach
    private void beforeSession() throws FileNotFoundException {
        System.out.println("beforeSession().........");
        if (factory == null){
            try {
                init();
            } catch (IOException e) {
                System.err.println(e.getLocalizedMessage());
            }
        }
        session = factory.openSession();
    }

    @Test
    public void testSave(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        Emp emp = new Emp();emp.setEmpno(1111);emp.setEname("Laowang");
        emp.setSal(25888.88);
        Calendar calendar = new GregorianCalendar();
        calendar.set(2022,0,28);
        emp.setHiredate(new Date(calendar.getTimeInMillis()));
        Integer save = mapper.save(emp);
        System.out.println(save);
    }
    @Test
    public void testDelete(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        Integer delete = mapper.delete(1111);
        Integer delete1 = mapper.delete(1112);
        System.out.println(delete);
        System.out.println(delete1);
    }
    @Test
    public void testUpdate(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        Emp emp = new Emp().buildEmpno(1111).buildSal(26888.88).buildHiredate(2022,0,28);
        Integer update = mapper.update(emp);
        System.out.println(update);
    }
    @Test
    public void testSelete(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        Emp emp = mapper.selectByEmpno(1111);
        System.out.println(emp);
    }
    @Test
    public void testSelectAll(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        List<Emp> emps = mapper.searchAll();
        emps.forEach(System.out::println);
    }
    /*
    * 批量增加，数据库设置自增，自动给我增加empno
    * */
    @Test
    public void testSaveBatch(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        List<Emp> emps = new ArrayList<>();
        emps.add(new Emp().buildEname("zhangsan").buildSal(17995.0).buildHiredate(2023,10,1));
        emps.add(new Emp().buildEname("lisi").buildSal(17345.0).buildHiredate(2023,9,1));
        emps.add(new Emp().buildEname("zhaoliu").buildSal(17534.0).buildHiredate(2024,11,1));
        emps.add(new Emp().buildEname("qianqi").buildSal(17362.0).buildHiredate(2024,0,1));
        emps.add(new Emp().buildEname("zhengba").buildSal(17439.0).buildHiredate(2025,3,1));
        Integer integer = mapper.saveBatch(emps);
        System.out.println(integer);
    }
    /*我在mapper.xml里添加了selectKey元素，希望添加随机的empno
    但是测试结果显然被数据库的自增主键值 覆盖掉了，从安全和健壮性角度，
    推荐自增主键，而非随机值蠢方法
    * */
    @Test
    public void testSaveWithRandomID(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        List<Emp> emps = new ArrayList<>();
        emps.add(new Emp().buildEname("随机ID").buildSal(17995.0).buildHiredate(2023,10,1));
        emps.add(new Emp().buildEname("随机ID").buildSal(17345.0).buildHiredate(2023,9,1));
        emps.add(new Emp().buildEname("随机ID").buildSal(17534.0).buildHiredate(2024,11,1));
        emps.add(new Emp().buildEname("随机ID").buildSal(17362.0).buildHiredate(2024,0,1));
        emps.add(new Emp().buildEname("随机ID").buildSal(17439.0).buildHiredate(2025,3,1));
        Integer integer = mapper.saveWithRandomID(emps);
        System.out.println(integer);
    }
    @Test
    public void testSelectByColumn(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        List<Emp> emps = mapper.selectByColumn("emp","empno", "1111");
        emps.forEach(System.out::println);
        System.out.println("------------");
        List<Emp> emps1 = mapper.selectByColumn("emp","ename", "随机ID");
        emps1.forEach(System.out::println);
    }

    @AfterEach
    private void afterSession(){
        System.out.println("afterSession().........");
        session.commit();
        session.close();
    }

}
