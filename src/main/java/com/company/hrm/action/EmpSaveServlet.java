package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.ServiceConst;
import com.company.hrm.common.SpringIoC;
import com.company.hrm.dao.pojo.Emp;
import com.company.hrm.service.iService.IEmpService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/EmpSaveServlet")
public class EmpSaveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empno = Integer.parseInt(request.getParameter("empno"));
        String ename = request.getParameter("ename");
        String job = request.getParameter("job");
        int mgr = Integer.parseInt(request.getParameter("mgr"));
        Date hiredate = null;

        try {
            hiredate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("hiredate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String sals = request.getParameter("sal");
        BigDecimal sal = null;
        if (!sals.isEmpty()) {
            sal = new BigDecimal(sals);
        }

        String comms = request.getParameter("comm");
        BigDecimal comm = null;
        if (!comms.isEmpty()) {
            comm = new BigDecimal(comms);
        }

        int deptno = Integer.parseInt(request.getParameter("deptno"));

        IEmpService empservice = (IEmpService) SpringIoC.getContext().getBean("empService");
        Emp emp = new Emp(empno,ename,job,mgr,hiredate,sal,comm,deptno);
        String msg = empservice.save(emp);
        System.out.println(msg);
        ResResult<Emp> res = null;
        if(msg.indexOf(ServiceConst.SUCCESS) != -1){
            res = ResResult.success("success");
        } else {
            res = ResResult.error(500, "save error");
        }
        String jsonResult = new ObjectMapper().writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.println(jsonResult);
        out.flush();
        out.close();
    }
}
