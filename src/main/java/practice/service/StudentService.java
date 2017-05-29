package practice.service;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import practice.domain.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.List;

@Service("studentService")
@Transactional
public class StudentService {
    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<Student> getAll() {
        logger.debug("Retrieving all students");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  Student");
        return  query.list();
    }

    public Student get( Integer id ) {
        Session session = sessionFactory.getCurrentSession();
        Student student = (Student) session.get(Student.class, id);
        return student;
    }

    public Student getByName( String name) {
        Session session = sessionFactory.getCurrentSession();
        Student student = (Student) session.createQuery("from Student S where S.name = :name")
                .setString("name",name).uniqueResult();
        return student;
    }
    public String getPassed(Student student) {
        Session session = sessionFactory.getCurrentSession();
        Object object = session.createQuery("select sum(points) from StudentExam SE " +
                "where SE.student = :student")
                .setParameter("student",student).uniqueResult();
        if (object == null)
            return "Did not passed";
        Long points = (Long) object;
        if (points>=10)
            return "Passed";
        else
            return "Did not passed";
    }
    
    public void add(Student student) {
        logger.debug("Adding new student");
        Session session = sessionFactory.getCurrentSession();
        session.save(student);
    }
    public void delete(Integer id) {
        logger.debug("Deleting existing student");
        Session session = sessionFactory.getCurrentSession();
        Student student = (Student) session.get(Student.class, id);
        session.delete(student);
    }

    public void edit(Student student) {
        logger.debug("Editing existing student");
        Session session = sessionFactory.getCurrentSession();
        Student existingStudent = (Student) session.get(Student.class, student.getId());
        existingStudent.setName(student.getName());
        session.save(existingStudent);
    }
}
