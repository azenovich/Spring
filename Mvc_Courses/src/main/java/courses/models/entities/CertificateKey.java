package courses.models.entities;

import courses.models.actors.Student;

import java.io.Serializable;
import java.util.Objects;

public class CertificateKey implements Serializable {
    private Course course;
    private Student student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CertificateKey that = (CertificateKey) o;
        return Objects.equals(course, that.course) &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {

        return Objects.hash(course, student);
    }
}
