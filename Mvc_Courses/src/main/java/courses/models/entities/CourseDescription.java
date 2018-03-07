package courses.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "coursesDescriptions")
public class CourseDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String information;

    @OneToMany(mappedBy = "description")
    private Set<Course> courses = new HashSet<>();
    @ManyToMany(mappedBy = "coursesDescription")
    private Set<Speciality> specialities = new HashSet<>();
}
