package org.krams.tutorial.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.krams.tutorial.domain.StudentExam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing StudentExams
 * Сервис для класса StudentExam
 */
@Service("studentExamService")
@Transactional
public class StudentExamService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all studentExams
     * Получает лист всех персон
     * @return a list of studentExams
     */
    public List<StudentExam> getAll() {
        logger.debug("Retrieving all studentExams");

        // Retrieve session from Hibernate
        // Получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        // Создаем запрос
        Query query = session.createQuery("FROM  StudentExam");

        // Retrieve all
        // получаем всех
        return  query.list();
    }

    /**
     * Retrieves a single studentExam
     * Получение одной персоны
     */
    public StudentExam get( Integer id ) {
        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing studentExam first
        // получаем персону по id
        StudentExam studentExam = (StudentExam) session.get(StudentExam.class, id);

        return studentExam;
    }
    /**
     * Adds a new studentExam
     *  Добавление персоны
     */
    public void add(StudentExam studentExam) {
        logger.debug("Adding new studentExam");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Save
        // сохраняем
        session.save(studentExam);
    }

    /**
     * Deletes an existing studentExam
     * Удаление существующей персоны
     * @param id the id of the existing studentExam
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing studentExam");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing studentExam first
        // получаем существующую персону
        StudentExam studentExam = (StudentExam) session.get(StudentExam.class, id);

        // Delete
        // удаляем
        session.delete(studentExam);
    }

    /**
     * Edits an existing studentExam
     * Правка персоны
     */
    public void edit(StudentExam studentExam) {
        logger.debug("Editing existing studentExam");

        // Retrieve session from Hibernate
        // как всегда получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing studentExam via id
        // получаем существующую персону по id
        StudentExam existingStudentExam = (StudentExam) session.get(StudentExam.class, studentExam.getId());

        // Assign updated values to this studentExam
        // обновляем значения
        existingStudentExam.setPoints(studentExam.getPoints());
        existingStudentExam.setStudent(studentExam.getStudent());
        existingStudentExam.setExam(studentExam.getExam());

        // Save updates
        // сохраняем изменения
        session.save(existingStudentExam);
    }
}
