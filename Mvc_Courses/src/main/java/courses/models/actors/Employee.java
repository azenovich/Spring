package courses.models.actors;


import javax.persistence.*;

@Entity
@Table(name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee extends Guest {
    @Column(nullable = false, length = 50)
    private String jobTitle;
    @Column(nullable = false, length = 200)
    private String professionalSkills;
    @Column(nullable = false)
    private float salary;
    @Column
    private float priorSalary;
}
