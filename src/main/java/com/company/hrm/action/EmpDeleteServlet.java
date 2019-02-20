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

@WebServlet(urlPatterns = "/EmpDeleteServlet")
public class EmpDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empno = Integer.parseInt(request.getParameter("empno"));
        Emp emp = new Emp();
        emp.setEmpno(empno);
        IEmpService empservice = (IEmpService) SpringIoC.getContext().getBean("empService");
        String msg = empservice.delete(emp);
        ResResult<Emp> res = null;
        if (msg.equals(ServiceConst.SUCCESS)) {
            res = ResResult.success("delete success!");
        }else {
            res = ResResult.error(500, "delete error");
        }
        String jsonRes = new ObjectMapper().writeValueAsString(res);
        PrintWriter out = response.getWriter();
        out.println(jsonRes);
        out.flush();
        out.close();
    }
}
