package courses.models.actors;

import courses.models.entities.CourseDescription;
import courses.models.entities.CourseInvite;
import courses.models.entities.Lesson;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "mentors")
public class Mentor extends Employee {
    @OneToMany(mappedBy = "mentor")
    private Set<Lesson> lessons = new HashSet<Lesson>();
    @OneToMany(mappedBy = "checkingMentor")
    private Set<CourseInvite> invites = new HashSet<CourseInvite>();
}
