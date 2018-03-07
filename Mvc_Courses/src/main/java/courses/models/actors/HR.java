package courses.models.actors;

import courses.models.entities.CourseInvite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "hrs")
public class HR extends Employee {
    @Column(nullable = true)
    private String workPhone;

    @OneToMany(mappedBy = "checkingHr")
    private Set<CourseInvite> invites = new HashSet<CourseInvite>();
}
