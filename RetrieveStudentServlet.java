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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author m&n Technology G Ltd
 */
public class RetrieveStudentServlet extends HttpServlet {

    
     public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><head><title>Student List</title></head><body>");
        out.println("<h1>Student List</h1>");
        out.println("<table border='1'><tr><th>ID</th><th>Username</th><th>Email</th><th>Action</th></tr>");

        StudentDao stdntDao = new StudentDao();
        List<Student> stdnts = stdntDao.getAllStudents();

        for (Student stdnt : stdnts) {
            out.println("<tr>");
            out.println("<td>" + stdnt.getId() + "</td>");
            out.println("<td>" + stdnt.getNames() + "</td>");
            out.println("<td>" + stdnt.getEmail() + "</td>");
            out.println("<td><a href='edit?id=" + stdnt.getId() + "'>Edit</a> | <a href='delete?id=" + stdnt.getId() + "'>Delete</a></td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
        out.close();
    }
   
        }


    