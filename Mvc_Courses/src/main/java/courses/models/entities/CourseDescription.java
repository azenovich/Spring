package courses.models.entities;

import courses.models.actors.Student;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coursesDescription")
public class CourseDescription {
    @Id
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String information;

    @OneToMany(mappedBy = "description")
    private Set<Course> courses = new HashSet<>();

    @ManyToMany(mappedBy = "coursesDescription")
    private Set<Speciality> specialities = new HashSet<>();
}
