package com.company.hrm.dao.idao;

import com.company.hrm.dao.pojo.Emp;

import java.util.List;

public interface IEmpDao extends IBaseDao<Emp, Integer>{
    List<Emp> findByName(String ename) throws Exception;
}
