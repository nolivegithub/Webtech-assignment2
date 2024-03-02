
package com.webtech;

import Dao.StudentDao;
import Model.Student;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author m&n Technology G Ltd
 */
public class DeleteStudentServlet extends HttpServlet {

     @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Long id = Long.parseLong(req.getParameter("id"));

        StudentDao studentdao = new StudentDao();
        Student stdnt = new Student();
        stdnt.setId(id);
        Student thestdnt = studentdao.findStudentById(stdnt);

        out.println("<html><head><title>Student deleted</title>");
        out.println("<style>");
        out.println(".button {");
        out.println("  padding: 10px 20px;");
        out.println("  font-size: 16px;");
        out.println("  cursor: pointer;");
        out.println("}");
        out.println(".delete {");
        out.println("  background-color: darkred;");
        out.println("  color: white;");
        out.println("}");
        out.println(".cancel {");
        out.println("  background-color: darkgreen;");
        out.println("  color: white;");
        out.println("}");
        out.println("</style>");
        out.println("</head><body>");
        out.println("<h1>Delete Student Confirmation</h1>");
        out.println("<p>Are you sure you want to delete Student " + thestdnt.getNames() + "?</p>");
        out.println("<form action='delete' method='post'>");
        out.println("<input type='hidden' name='id' value='" + id + "'>");
        out.println("<button class='button delete' type='submit'>Delete</button>");
        out.println("</form>");
        out.println("<form action='/cancel' method='get'>");
        out.println("<button class='button cancel' type='submit'>Cancel</button>");
        out.println("</form>");
        out.println("</body></html>");

        out.close();
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Long id = Long.parseLong(req.getParameter("id"));

        StudentDao studentdao = new StudentDao();
        Student stdnt = new Student();
        stdnt.setId(id);
        Student thestdnt = studentdao.findStudentById(stdnt);
        stdnt = studentdao.DeleteStudent(stdnt);
        if(stdnt!=null){
            res.sendRedirect("view");
        }
    }


}
