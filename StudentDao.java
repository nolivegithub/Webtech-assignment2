package Dao;

import java.util.List;
import Model.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
/**
 *
 * @author m&n Technology G Ltd
 */
public class StudentDao {
      public Student createStudent(Student student){
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.save(student);
            ss.beginTransaction().commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        }
    public Student updateStudent(Student student) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.update(student);
            ss.beginTransaction().commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Student DeleteStudent(Student student) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            ss.delete(student);
            ss.beginTransaction().commit();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Student findStudentById(Student student) {
        try {
            Session ss = HibernateUtil.getSessionFactory().openSession();
            Student theStudent =(Student)ss.get(Student.class, student.getId());
            return theStudent;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Student> getAllStudents() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            List<Student> students = session.createQuery("FROM Student").list();
            session.close(); 
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student findByEmail(Student student) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();

            Criteria criteria = session.createCriteria(Student.class);
            criteria.add(Restrictions.eq("email", student.getEmail()));

            Student foundStudent = (Student) criteria.uniqueResult();

            session.close();

            return foundStudent;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
