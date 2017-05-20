package org.krams.tutorial.service;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.krams.tutorial.domain.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service for processing Students
 * Сервис для класса Student
 */
@Service("studentService")
@Transactional
public class StudentService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Retrieves all students
     * Получает лист всех персон
     * @return a list of students
     */
    public List<Student> getAll() {
        logger.debug("Retrieving all students");

        // Retrieve session from Hibernate
        // Получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Create a Hibernate query (HQL)
        // Создаем запрос
        Query query = session.createQuery("FROM  Student");

        // Retrieve all
        // получаем всех
        return  query.list();
    }

    /**
     * Retrieves a single student
     * Получение одной персоны
     */
    public Student get( Integer id ) {
        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing student first
        // получаем персону по id
        Student student = (Student) session.get(Student.class, id);

        return student;
    }



    public Student getByName( String name) {
        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing student first
        // получаем персону по id
        Student student = (Student) session.createQuery("from Student S where S.name = :name")
                .setString("name",name).uniqueResult();

        return student;
    }

    public String getPassed(Student student) {
        Session session = sessionFactory.getCurrentSession();

        Object object = (Long) session.createQuery("select sum(points) from StudentExam SE " +
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
    /**
     * Adds a new student
     *  Добавление персоны
     */
    public void add(Student student) {
        logger.debug("Adding new student");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Save
        // сохраняем
        session.save(student);
    }

    /**
     * Deletes an existing student
     * Удаление существующей персоны
     * @param id the id of the existing student
     */
    public void delete(Integer id) {
        logger.debug("Deleting existing student");

        // Retrieve session from Hibernate
        // получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing student first
        // получаем существующую персону
        Student student = (Student) session.get(Student.class, id);

        // Delete
        // удаляем
        session.delete(student);
    }

    /**
     * Edits an existing student
     * Правка персоны
     */
    public void edit(Student student) {
        logger.debug("Editing existing student");

        // Retrieve session from Hibernate
        // как всегда получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing student via id
        // получаем существующую персону по id
        Student existingStudent = (Student) session.get(Student.class, student.getId());

        // Assign updated values to this student
        // обновляем значения
        existingStudent.setName(student.getName());

        // Save updates
        // сохраняем изменения
        session.save(existingStudent);
    }
}
