package courses.models.actors;


import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee extends Student {
    @Column(nullable = false, length = 50)
    private String jobTitle;

    @Column(nullable = false)
    private float salary;
    @Column
    private float priorSalary;
}
