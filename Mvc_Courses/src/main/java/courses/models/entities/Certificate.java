package courses.models.entities;

import courses.models.actors.Student;

import javax.persistence.*;

@Entity
@Table(name = "certificates")
@IdClass(CertificateKey.class)
public class Certificate {
    @Id
    @ManyToOne(optional = false)
    private Course course;
    @Id
    @ManyToOne(optional = false)
    private Student student;
}
