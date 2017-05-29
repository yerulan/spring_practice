package practice.service;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import practice.domain.StudentExam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("studentExamService")
@Transactional
public class StudentExamService {
    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public List<StudentExam> getAll() {
        logger.debug("Retrieving all studentExams");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  StudentExam");
        return  query.list();
    }

    public StudentExam get( Integer id ) {        Session session = sessionFactory.getCurrentSession();
        StudentExam studentExam = (StudentExam) session.get(StudentExam.class, id);

        return studentExam;
    }

    public void add(StudentExam studentExam) {
        logger.debug("Adding new studentExam");
        Session session = sessionFactory.getCurrentSession();
        session.save(studentExam);
    }
    public void delete(Integer id) {
        logger.debug("Deleting existing studentExam");
        Session session = sessionFactory.getCurrentSession();
        StudentExam studentExam = (StudentExam) session.get(StudentExam.class, id);
        session.delete(studentExam);
    }

    public void edit(StudentExam studentExam) {
        logger.debug("Editing existing studentExam");
        Session session = sessionFactory.getCurrentSession();
        StudentExam existingStudentExam = (StudentExam) session.get(StudentExam.class, studentExam.getId());
        existingStudentExam.setPoints(studentExam.getPoints());
        existingStudentExam.setStudent(studentExam.getStudent());
        existingStudentExam.setExam(studentExam.getExam());
        session.save(existingStudentExam);
    }
}
