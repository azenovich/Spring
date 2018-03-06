package courses.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialities")
public class Speciality {
    @Id
    private int id;
    @Column(nullable = false)
    private String name;

    @ManyToMany()
    @JoinTable(
            name = "specialities_coursesDescription",
            joinColumns = { @JoinColumn(name = "specialities_id") },
            inverseJoinColumns = { @JoinColumn(name = "coursesDescription_id") }
    )
    private Set<CourseDescription> coursesDescription = new HashSet<>();
    @OneToMany(mappedBy = "speciality")
    private Set<Lesson> lessons = new HashSet<Lesson>();
}
