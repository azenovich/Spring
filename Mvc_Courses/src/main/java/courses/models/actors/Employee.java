package courses.models.actors;


import javax.persistence.*;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends Guest {
    @Column(nullable = true, length = 50)
    private String jobTitle;
    @Column(nullable = true, length = 200)
    private String professionalSkills;
    @Column(nullable = true)
    private float salary;
    @Column(nullable = true)
    private float priorSalary;
}
