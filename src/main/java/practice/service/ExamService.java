package practice.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import practice.domain.Exam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("examService")
@Transactional
public class ExamService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;
    public List<Exam> getAll() {
        logger.debug("Retrieving all exams");
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM  Exam");
        return  query.list();
    }
    public Exam get( Integer id ) {
        Session session = sessionFactory.getCurrentSession();
        Exam exam = (Exam) session.get(Exam.class, id);
        return exam;
    }
    public Exam getByName(String name ) {
        Session session = sessionFactory.getCurrentSession();
        Exam exam = (Exam) session.createQuery("from Exam E where E.name = :name")
                .setString("name",name).uniqueResult();
        return exam;
    }
    public void add(Exam exam) {
        logger.debug("Adding new exam");
        Session session = sessionFactory.getCurrentSession();
        session.save(exam);
    }
    public void delete(Integer id) {
        logger.debug("Deleting existing exam");
        Session session = sessionFactory.getCurrentSession();
        Exam exam = (Exam) session.get(Exam.class, id);
        session.delete(exam);
    }
    public void edit(Exam exam) {
        logger.debug("Editing existing exam");
        Session session = sessionFactory.getCurrentSession();
        Exam existingExam = (Exam) session.get(Exam.class, exam.getId());
        existingExam.setName(exam.getName());
        session.save(existingExam);
    }
}
