
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
public class UpdateStudentServlet  extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Long id = Long.parseLong(req.getParameter("id"));
        Student thestdnt = new Student();
        thestdnt.setId(id);

        StudentDao stdntdao = new StudentDao();
        Student stdnt = stdntdao.findStudentById(thestdnt);

        out.println("<html><head><title>Edit Student</title></head><body>");
        out.println("<h1>Edit Student</h1>");
        out.println("<form action='edit' method='post'>");
        out.println("<input type='hidden' name='id' value='" + id + "'>");
        out.println("Name: <input type='text' name='name' value='" + stdnt.getNames() + "'><br>");
        out.println("Email: <input type='email' name='email' value='" + stdnt.getEmail() + "'><br>");
        out.println("<input type='hidden' name='password' value='" + stdnt.getPassword() + "'>");
        out.println("Password: <input type='password' value='********' disabled><br>");
        out.println("<input type='submit' value='Update'>");
        out.println("</form>");
        out.println("</body></html>");

        out.close();
    }

    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        Student stdnt = new Student();
        stdnt.setId(id);
        stdnt.setNames(name);
        stdnt.setEmail(email);
        stdnt.setPassword(password);
        StudentDao studentDao = new StudentDao();
        Student thestdnt = studentDao.updateStudent(stdnt);
        if(thestdnt != null){
            res.sendRedirect("view");
        }else{
            res.getWriter().println("ERROR: Editing unsuccesfull");
        }
        
    }

}
