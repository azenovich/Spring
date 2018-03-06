package courses.models.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "statusCourseInvites")
public class StatusCourseInvite {
    @Id
    private int id;
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "status")
    private Set<CourseInvite> courseInvites = new HashSet<>();
}

