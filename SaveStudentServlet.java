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
public class SaveStudentServlet extends HttpServlet {

  public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException{
      String name=req.getParameter("name");
      String email=req.getParameter("email");
      String password=req.getParameter("password");
      
      if (name==null || name.trim().isEmpty() || email==null || email.trim().isEmpty()
          || password==null || password.trim().isEmpty()
          ||!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$")
          ||password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!])(?!.*\\s).{8,}$")){
              
              res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
              res.getWriter().println("incorrect credential. please enter corret email,name, and password.");
              return;
      }
      
 String hashedPassword = Password.hashPassword(password);
        // Creating the stdnt object and DAO to interact with the database
        Student stdnt = new Student();
        stdnt.setNames(name);
        stdnt.setEmail(email);
        stdnt.setPassword(hashedPassword);
        
        StudentDao stdntDao= new StudentDao();
        
        // Checking if the Email is unique and raising an error if it is not unique
        Student thestdnt = stdntDao.findByEmail(stdnt);
        if (thestdnt != null) {
            res.setContentType("text/html");

            PrintWriter out = res.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Email not available</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; text-align: center; }");
            out.println(".message { color: red; font-size: 20px; }");
            out.println(".button { background-color: #4CAF50; border: none; color: white; padding: 15px 32px;");
            out.println("text-align: center; text-decoration: none; display: inline-block; font-size: 16px; margin: 4px 2px;");
            out.println("cursor: pointer; border-radius: 12px; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<p class='message'>The email has been used. Please using another email.</p>");
            out.println("<button class='button' onclick='window.location.href=\"signup.html\";'>Go Back</button>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
        // Saving the stdnt
        stdnt = stdntDao.createStudent(stdnt);
        if(stdnt != null){
            res.sendRedirect("index.html");
        }
        
}
    
}


