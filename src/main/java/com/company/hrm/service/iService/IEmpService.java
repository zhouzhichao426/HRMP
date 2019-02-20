package com.company.hrm.service.iService;

import com.company.hrm.dao.pojo.Emp;

import java.util.List;

public interface IEmpService extends IBaseService<Emp,Integer>{
    List<Emp> findByName(String ename);
}
