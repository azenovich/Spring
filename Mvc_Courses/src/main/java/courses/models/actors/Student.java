package courses.models.actors;

import courses.models.entities.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
@Inheritance(strategy = InheritanceType.JOINED)
public class Student extends Guest{
    @Column(nullable = true, length = 200)
    private String professionalSkills;

    @OneToMany(mappedBy = "student")
    private Set<CourseInvite> invites = new HashSet<CourseInvite>();
    @OneToMany(mappedBy = "student")
    private Set<Certificate> certificates = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
