package com.company.hrm.action;

import com.company.hrm.common.ResResult;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/EmpFindByIdServlet")
public class EmpFindByIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int empno = Integer.parseInt(request.getParameter("empno"));
        IEmpService empservice = (IEmpService) SpringIoC.getContext().getBean("empService");
        Emp emp = null;
        ResResult<List<Emp>> res = null;
        List<Emp> empList = new ArrayList<Emp>();
        if (empservice.findById(empno) != null) {
            emp = empservice.findById(empno);
            empList.add(emp);
            res = ResResult.success("find success",empList);
        }else {
            res = ResResult.error(404, "no data");
        }
        PrintWriter out = response.getWriter();
        String jsonRes = new ObjectMapper().writeValueAsString(res);
        System.out.println(jsonRes);
        out.println(jsonRes);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
