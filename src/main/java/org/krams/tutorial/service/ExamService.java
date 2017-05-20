package org.krams.tutorial.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.krams.tutorial.domain.Exam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing Exams
 * Сервис для класса Exam
 */
@Service("examService")
@Transactional
public class ExamService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all exams
     * Получает лист всех персон
     * @return a list of exams
     */
    public List<Exam> getAll() {
        logger.debug("Retrieving all exams");

        // Retrieve session from Hibernate
        // Получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        // Создаем запрос
        Query query = session.createQuery("FROM  Exam");

        // Retrieve all
        // получаем всех
        return  query.list();
    }

    /**
     * Retrieves a single exam
     * Получение одной персоны
     */
    public Exam get( Integer id ) {
        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing exam first
        // получаем персону по id
        Exam exam = (Exam) session.get(Exam.class, id);

        return exam;
    }

    public Exam getByName(String name ) {
        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing exam first
        // получаем персону по id
        Exam exam = (Exam) session.createQuery("from Exam E where E.name = :name")
                .setString("name",name).uniqueResult();

        return exam;
    }
    /**
     * Adds a new exam
     *  Добавление персоны
     */
    public void add(Exam exam) {
        logger.debug("Adding new exam");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Save
        // сохраняем
        session.save(exam);
    }

    /**
     * Deletes an existing exam
     * Удаление существующей персоны
     * @param id the id of the existing exam
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing exam");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing exam first
        // получаем существующую персону
        Exam exam = (Exam) session.get(Exam.class, id);

        // Delete
        // удаляем
        session.delete(exam);
    }

    /**
     * Edits an existing exam
     * Правка персоны
     */
    public void edit(Exam exam) {
        logger.debug("Editing existing exam");

        // Retrieve session from Hibernate
        // как всегда получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing exam via id
        // получаем существующую персону по id
        Exam existingExam = (Exam) session.get(Exam.class, exam.getId());

        // Assign updated values to this exam
        // обновляем значения
        existingExam.setName(exam.getName());

        // Save updates
        // сохраняем изменения
        session.save(existingExam);
    }
}
