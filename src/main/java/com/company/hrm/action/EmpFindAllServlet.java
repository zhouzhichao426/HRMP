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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/EmpFindAllServlet")
public class EmpFindAllServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //判断用户是否登录
        HttpSession session = request.getSession();
        ResResult<List<Emp>> result = null;
        if (session.getAttribute("username") != null) {
            //执行
            IEmpService empservice = (IEmpService) SpringIoC.getContext().getBean("empService");
            List<Emp> empList = new ArrayList<Emp>();
            empList = empservice.findAll();
            if (!empList.isEmpty() && empList.size() > 0) {
                result = ResResult.success("find all success",empList);
            }else {
                result = ResResult.error(404, "no data");
            }
        }else {
            result = ResResult.error(301, "have not login");
        }
        PrintWriter out = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(result);
        out.println(jsonResult);
        out.flush();
        out.close();
    }

}
