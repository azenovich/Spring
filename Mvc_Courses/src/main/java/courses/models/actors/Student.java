package courses.models.actors;

import courses.models.entities.Course;
import courses.models.entities.CourseDescription;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Inheritance(strategy = InheritanceType.JOINED)
public class Student extends Guest{
    @Column(nullable = false, length = 200)
    private String professionalSkills;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses = new HashSet<Course>();
}
