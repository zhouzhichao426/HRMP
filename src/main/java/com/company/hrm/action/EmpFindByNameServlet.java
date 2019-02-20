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
import java.util.List;

@WebServlet(urlPatterns = "/EmpFindByNameServlet")
public class EmpFindByNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ename = request.getParameter("ename");
        IEmpService empservice = (IEmpService) SpringIoC.getContext().getBean("empService");
        List<Emp> empList = empservice.findByName(ename);
        ResResult<List<Emp>> res = null;

        if (!empList.isEmpty() && empList.size()>0) {
            res = ResResult.success("success",empList);
            System.out.println(res);
        }else {
            res = ResResult.error(404, "no such data");
        }
        PrintWriter out = response.getWriter();
        String jsonRes = new ObjectMapper().writeValueAsString(res);
        System.out.println(jsonRes);
        out.println(jsonRes);
        out.flush();
        out.close();
    }
}
