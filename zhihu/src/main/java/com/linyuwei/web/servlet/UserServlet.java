package com.linyuwei.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linyuwei.pojo.User;
import com.linyuwei.pojo.UserCredentials;
import com.linyuwei.service.UserService;
import com.linyuwei.service.impl.UserServiceImpl;
import com.linyuwei.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService = new UserServiceImpl();
    private String checkCodeGen;
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 使用ObjectMapper来解析JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // 从请求体中读取数据
        BufferedReader reader = request.getReader();
        String line;
        StringBuilder requestBody = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // 将请求体中的JSON字符串转换为Java对象
        UserCredentials credentials = objectMapper.readValue(requestBody.toString(), UserCredentials.class);

        // 现在可以从credentials对象中获取数据
        String remember = credentials.isRemember();
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        User user = userService.login(username, password);
        response.setContentType("text/plain;charset=UTF-8");
        if(user!=null){
            //登录成功,跳转到查询所有的BrandServlet
            //把登录成功后的User对象存起来
            HttpSession session = request.getSession(true);
            System.out.print("true".equals(remember));
            if("true".equals(remember)){
                //勾选
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password", password);
                c_username.setMaxAge(60*60*34*7);
                c_password.setMaxAge(60*60*24*7);
                response.addCookie(c_username);
                response.addCookie(c_password);
                System.out.print(c_username);
            }
            session.setAttribute("user",user);
//            String contextPath = request.getContextPath();
            response.getWriter().write("success");
//            response.sendRedirect(contextPath+"/brand/selectAll");
        }else {
            //登陆失败

            //存储错误信息到request
//            request.setAttribute("login_msg","用户名或密码错误");
            response.getWriter().write("fail");
            //跳转到login.jsp
//            request.getRequestDispatcher("/login.html").forward(request,response);
        }
    }

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        // 从请求体中读取数据
        BufferedReader reader = request.getReader();
        String line;
        StringBuilder requestBody = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            requestBody.append(line);
        }

        // 将请求体中的JSON字符串转换为Java对象
        UserCredentials credentials = objectMapper.readValue(requestBody.toString(), UserCredentials.class);

        // 现在可以从credentials对象中获取数据
//        String checkCode = credentials.getCheckCode();
        String username = credentials.getUsername();
        String password = credentials.getPassword();


        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        response.setContentType("text/plain;charset=UTF-8");

//        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
//            response.getWriter().write("fail");
//            return;
//        }


        boolean flag = userService.register(user);
        if(flag){
            response.getWriter().write("success");
        }else{
            response.getWriter().write("fail");
        }
    }
//    public void checkCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        ServletOutputStream os = response.getOutputStream();
//        String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
//        //将他存入session
//        checkCodeGen = checkCode;
//    }

}
