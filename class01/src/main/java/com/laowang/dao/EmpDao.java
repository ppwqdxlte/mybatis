package com.laowang.dao;

import com.laowang.bean.Emp;

public interface EmpDao {

    public Integer save(Emp emp);

    public Integer delete(Integer empno);

    public Integer update(Emp emp);

    public Emp selectByEmpno(Integer empno);
}
