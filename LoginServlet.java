/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webtech;

import Dao.StudentDao;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author m&n Technology G Ltd
 */
public class LoginServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        
        Student stdnt= new Student();
        stdnt.setEmail(email);
        StudentDao stdntdao=new StudentDao();
        Student thestdnt = stdntdao.findByEmail(stdnt);
        
        if(thestdnt != null){
            
            if(Password.checkPassword(password,thestdnt.getPassword())){
                HttpSession session = req.getSession();
                 session.setAttribute("stdnt", thestdnt);
                res.sendRedirect("view");
            }else{
                res.sendRedirect("forget.html");
            }
            
    }else{
            res.getWriter().print("Login failed");
            }
        }
    }


