package practice.controller;

import org.apache.log4j.Logger;
import practice.domain.Exam;
import practice.domain.Student;
import practice.domain.StudentExam;
import practice.service.ExamService;
import practice.service.StudentExamService;
import practice.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/main")
public class MainController {

    private static Logger logger = Logger.getLogger("controller");

    @Resource(name="studentService")
    private StudentService studentService;

    @Resource(name="studentExamService")
    private StudentExamService studentExamService;

    @Resource(name="examService")
    private ExamService examService;

    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public String getStudents(Model model) throws SQLException {

        logger.debug("Received request to show all students");

        List<Student> students = studentService.getAll();

        model.addAttribute("students", students);
        model.addAttribute("studentService", studentService);

        return "students";
    }

    @RequestMapping(value = "/students/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Received request to show add page");

        model.addAttribute("studentAttribute", new Student());

        return "addStudent";
    }

    @RequestMapping(value = "/students/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("studentAttribute") Student student) throws SQLException, InterruptedException {
        logger.debug("Received request to add new student");
        studentService.add(student);
        return "redirect:/krams/main/students";
    }
    @RequestMapping(value = "/students/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id") Integer id,
                         Model model) throws SQLException {

        logger.debug("Received request to delete existing student");
        studentService.delete(id);

        model.addAttribute("id", id);

        return "redirect:/krams/main/students";
    }
    @RequestMapping(value = "/students/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value="id") Integer id,
                          Model model) throws SQLException {
        logger.debug("Received request to show edit page");

        model.addAttribute("studentAttribute", studentService.get(id));

        return "editStudent";
    }

    @RequestMapping(value = "/students/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("studentAttribute") Student student,
                           @RequestParam(value="id") Integer id,
                           Model model) throws SQLException {
        logger.debug("Received request to update student");

        student.setId(id);

        studentService.edit(student);

        model.addAttribute("id", id);

        return "redirect:/krams/main/students";
    }

    @RequestMapping(value = "/studentExams", method = RequestMethod.GET)
    public String getStudentExam(Model model) throws SQLException {

        logger.debug("Received request to show all studentExams");
        List<StudentExam> studentExams = studentExamService.getAll();
        model.addAttribute("studentExams", studentExams);

        return "studentExams";
    }

    @RequestMapping(value = "/studentExams/add", method = RequestMethod.GET)
    public String getAddStudentExam(Model model) {
        logger.debug("Received request to show add page(studentExam)");
        model.addAttribute("studentExamAttribute", new StudentExam());
        return "addStudentExam";
    }
    @RequestMapping(value = "/studentExams/add", method = RequestMethod.POST)
    public String addStudentExam(@ModelAttribute("studentExamAttribute") StudentExam studentExam) throws SQLException {
        logger.debug("Received request to add new studentExam");
        Student student=studentService.getByName(studentExam.getStudent().getName());
        Exam exam=examService.getByName(studentExam.getExam().getName());
        studentExam.setStudent(student);
        studentExam.setExam(exam);
        studentExamService.add(studentExam);
        return "redirect:/krams/main/studentExams";
    }

    @RequestMapping(value = "/studentExams/delete", method = RequestMethod.GET)
    public String deleteStudentExam(@RequestParam(value="id") Integer id,
                         Model model) throws SQLException {

        logger.debug("Received request to delete existing studentExam");

        studentExamService.delete(id);

        model.addAttribute("id", id);

        return "redirect:/krams/main/studentExams";
    }
    @RequestMapping(value = "/studentExams/edit", method = RequestMethod.GET)
    public String getStudentExamEdit(@RequestParam(value="id") Integer id,
                          Model model) throws SQLException {
        logger.debug("Received request to show edit page");
        model.addAttribute("studentExamAttribute", studentExamService.get(id));
        return "editStudentExam";
    }
    @RequestMapping(value = "/studentExams/edit", method = RequestMethod.POST)
    public String saveStudentExamEdit(@ModelAttribute("studentExamAttribute") StudentExam studentExam,
                           @RequestParam(value="id") Integer id,
                           Model model) throws SQLException {
        logger.debug("Received request to update studentExam");
        studentExam.setId(id);
        Student student=studentService.getByName(studentExam.getStudent().getName());
        Exam exam=examService.getByName(studentExam.getExam().getName());
        studentExam.setStudent(student);
        studentExam.setExam(exam);
        studentExamService.edit(studentExam);
        model.addAttribute("id", id);
        return "redirect:/krams/main/studentExams";
    }


    @RequestMapping(value = "/exams", method = RequestMethod.GET)
    public String getExam(Model model) throws SQLException {

        logger.debug("Received request to show all exams");
        List<Exam> exams = examService.getAll();

        model.addAttribute("exams", exams);
        return "exams";
    }

    @RequestMapping(value = "/exams/add", method = RequestMethod.GET)
    public String getAddExam(Model model) {
        logger.debug("Received request to show add page(exam)");

        model.addAttribute("examAttribute", new Exam());
        return "addExam";
    }

    @RequestMapping(value = "/exams/add", method = RequestMethod.POST)
    public String addExam(@ModelAttribute("examAttribute") Exam exam) throws SQLException {
        logger.debug("Received request to add new exam");

        examService.add(exam);
        return "redirect:/krams/main/exams";
    }

    @RequestMapping(value = "/exams/delete", method = RequestMethod.GET)
    public String deleteExam(@RequestParam(value="id") Integer id,
                             Model model) throws SQLException {

        logger.debug("Received request to delete existing exam");

        examService.delete(id);

        model.addAttribute("id", id);

        return "redirect:/krams/main/exams";
    }

    @RequestMapping(value = "/exams/edit", method = RequestMethod.GET)
    public String getExamEdit(@RequestParam(value="id") Integer id,
                              Model model) throws SQLException {
        logger.debug("Received request to show edit page");
        model.addAttribute("examAttribute", examService.get(id));
        return "editExam";
    }
    @RequestMapping(value = "/exams/edit", method = RequestMethod.POST)
    public String saveExamEdit(@ModelAttribute("examAttribute") Exam exam,
                               @RequestParam(value="id") Integer id,
                               Model model) throws SQLException {
        logger.debug("Received request to update exam");

        exam.setId(id);

        examService.edit(exam);

        model.addAttribute("id", id);

        return "redirect:/krams/main/exams";
    }

}