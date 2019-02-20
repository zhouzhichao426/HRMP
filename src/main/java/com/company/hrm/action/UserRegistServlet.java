package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.ServiceConst;
import com.company.hrm.common.SpringIoC;
import com.company.hrm.dao.pojo.User;
import com.company.hrm.service.iService.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/UserRegistServlet")
public class UserRegistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String userpassword1 = request.getParameter("userpassword");
        System.out.println(username);
        System.out.println(userpassword1);
        IUserService userservice = (IUserService) SpringIoC.getContext().getBean("userService");
        User user = new User(username, userpassword1, 1);
        ResResult res = null;
        String a = userservice.regist(user);
        if(a.equals(ServiceConst.SUCCESS)){
            res = ResResult.success("regist success",user);
        } else {
            res = ResResult.error(500, "regist error");
        }
        PrintWriter out = response.getWriter();
        String jsonResult = new ObjectMapper().writeValueAsString(res);
        out.write(jsonResult);
        out.flush();
        out.close();
    }

}
