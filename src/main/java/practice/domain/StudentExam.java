package practice.domain;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tasks")
public class StudentExam implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name="student")

    private Student student;


    @ManyToOne
    @JoinColumn(name="exam")

    private Exam exam;

    @Column(name = "points")

    private int points;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}