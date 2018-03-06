package courses.models.entities;

import courses.models.actors.Mentor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    private int id;

    @ManyToOne(optional = false)
    private Mentor mentor;
    @ManyToOne(optional = false)
    private Course course;
    @ManyToOne(optional = false)
    private Speciality speciality;
}
