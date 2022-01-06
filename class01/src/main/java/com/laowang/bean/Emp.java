package com.laowang.bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

public class Emp implements Serializable {

    private Integer empno;
    private String ename;
    private Integer deptno;
    private Integer mgr;
    private String job;
    private Double sal;
    private Date hiredate;
    private String comm;

    public Emp buildEmpno(Integer empno){
        this.empno = empno;
        return this;
    }
    public Emp buildEname(String ename){
        this.ename = ename;
        return this;
    }
    public Emp buildDeptno(Integer deptno){
        this.deptno = deptno;
        return this;
    }
    public Emp buildMgr(Integer mgr){
        this.mgr = mgr;
        return this;
    }
    public Emp buildJob(String job){
        this.job = job;
        return this;
    }
    public Emp buildSal(Double sal){
        this.sal = sal;
        return this;
    }
    public Emp buildHiredate(Integer year,Integer month,Integer day){
        this.parseIntToDate(year,month,day);
        return this;
    }
    private Date parseIntToDate(Integer year,Integer month,Integer day){
        Calendar calendar = new GregorianCalendar();
        calendar.set(year,month,day);
        this.setHiredate(new Date(calendar.getTimeInMillis()));
        return this.hiredate;
    }
    public Emp buildComm(String comm){
        this.comm = comm;
        return this;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", deptno=" + deptno +
                ", mgr=" + mgr +
                ", job='" + job + '\'' +
                ", sal=" + sal +
                ", hiredate=" + hiredate +
                ", comm='" + comm + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emp emp = (Emp) o;
        return Objects.equals(empno, emp.empno) && Objects.equals(ename, emp.ename) && Objects.equals(deptno, emp.deptno) && Objects.equals(mgr, emp.mgr) && Objects.equals(job, emp.job) && Objects.equals(sal, emp.sal) && Objects.equals(hiredate, emp.hiredate) && Objects.equals(comm, emp.comm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empno, ename, deptno, mgr, job, sal, hiredate, comm);
    }

    public Emp() {
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public Integer getMgr() {
        return mgr;
    }

    public void setMgr(Integer mgr) {
        this.mgr = mgr;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getComm() {
        return comm;
    }

    public void setComm(String comm) {
        this.comm = comm;
    }
}
