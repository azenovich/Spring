package courses.models.entities;

import courses.models.actors.Student;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    private int id;
    @Column(nullable = false)
    private Date dateIn;
    @Column(nullable = false)
    private Date dateOut;
    @ManyToOne(optional = false)
    private CourseDescription description;

    @ManyToMany()
    @JoinTable(
            name = "courses_students",
            joinColumns = { @JoinColumn(name = "course_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") }
    )
    private Set<Student> students = new HashSet<>();
}
