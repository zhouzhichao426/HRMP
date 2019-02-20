package com.company.hrm.action;

import com.company.hrm.common.ResResult;
import com.company.hrm.common.SpringIoC;
import com.company.hrm.service.iService.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/UserExistServlet")
public class UserExistServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        IUserService userService = (IUserService) SpringIoC.getContext().getBean("userService");
        boolean flag = userService.isExist(username);
        ResResult result = flag? ResResult.success() : ResResult.error(404, "no such user");
        String resultJson = new ObjectMapper().writeValueAsString(result);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        out.println(resultJson);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
