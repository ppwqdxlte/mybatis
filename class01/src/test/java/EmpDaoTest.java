import com.laowang.bean.Emp;
import com.laowang.dao.EmpDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
        Emp emp = new Emp();
        emp.setEmpno(1112);
        emp.setSal(26888.88);
        Integer update = mapper.update(emp);
        System.out.println(update);
    }
    @Test
    public void testSelete(){
        EmpDao mapper = session.getMapper(EmpDao.class);
        Emp emp = mapper.selectByEmpno(1111);
        System.out.println(emp);
    }

    @AfterEach
    private void afterSession(){
        System.out.println("afterSession().........");
        session.commit();
        session.close();
    }
}
