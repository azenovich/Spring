package courses.models.entities;

import courses.models.actors.Mentor;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Mentor mentor;
    @ManyToOne(optional = false)
    private Course course;
    @ManyToOne(optional = false)
    private Speciality speciality;
}
