package com.laowang.dao;

import com.laowang.bean.Emp;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface EmpDao {

    public Integer save(Emp emp);

    public Integer delete(Integer empno);

    public Integer update(Emp emp);

    public Emp selectByEmpno(Integer empno);

    @Select(value = "select * from emp")
    public List<Emp> searchAll();

    public Integer saveBatch(List<Emp> empList);

    public Integer saveWithRandomID(List<Emp> empList);
    /*动态生成表名或者列名时候:
    * 【提示】 用这种方式接受用户的输入，并用作语句参数是不安全的，
    * 会导致潜在的 SQL 注入攻击。
    * 因此，要么不允许用户输入这些字段，要么自行转义并检验这些参数。*/
    public List<Emp> selectByColumn(@Param("table") String table,@Param("column") String column,@Param("value") String value);

}
