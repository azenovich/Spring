package courses.models.entities;

import courses.models.actors.HR;
import courses.models.actors.Mentor;
import courses.models.actors.Student;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "courseInvites")
public class CourseInvite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date filingDate;

    @ManyToOne(optional = false)
    private Student student;
    @ManyToOne(optional = false)
    private Course course;
    @ManyToOne(optional = true)
    private HR checkingHr;
    @ManyToOne(optional = true)
    private Mentor checkingMentor;
    @ManyToOne(optional = true)
    private StatusCourseInvite status;
}
