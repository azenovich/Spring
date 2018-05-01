package by.bsuir.recourse.entity.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "hometask_solution")
public class HometaskSolution extends BaseEntity<Integer> {

    @NotNull
    @Column(columnDefinition = "INT(11)", nullable = false)
    private Integer lessonId;

    @NotNull
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id")
    private User student;

    @NotNull
    @SafeHtml
    @NotEmpty
    @Column(columnDefinition = "TEXT", nullable = false)
    private String solution;


    public HometaskSolution() {
    }

    public HometaskSolution(Integer lessonId, User student, String solution) {
        this.lessonId = lessonId;
        this.student = student;
        this.solution = solution;
    }

    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HometaskSolution that = (HometaskSolution) o;
        return Objects.equals(lessonId, that.lessonId) &&
                Objects.equals(student, that.student) &&
                Objects.equals(solution, that.solution);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), lessonId, student, solution);
    }
}
