package courses.models.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
    @Column(nullable = false)
    private Date dateInInvite;
    @Column(nullable = false)
    private Date dateOutInvite;
    @Column
    private int studentsCount;

    @OneToMany(mappedBy = "course")
    private Set<CourseInvite> invites = new HashSet<CourseInvite>();
    @OneToMany(mappedBy = "course")
    private Set<Lesson> lessons = new HashSet<Lesson>();
    @OneToMany(mappedBy = "course")
    private Set<Certificate> certificates = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
