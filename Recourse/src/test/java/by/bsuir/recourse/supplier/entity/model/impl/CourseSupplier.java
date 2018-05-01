package by.bsuir.recourse.supplier.entity.model.impl;

import by.bsuir.recourse.entity.model.Course;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.supplier.entity.model.EntityIntegerPKSupplier;

public class CourseSupplier implements EntityIntegerPKSupplier<Course> {
    private UserSupplier userSupplier = new UserSupplier();

    @Override
    public Course getValidEntityWithoutId() {
        Course course =  new Course();
        course.setTitle("Title");
        course.setDescription("Description");
        course.setMaxStudents(10);
        course.setStatus(Course.Status.ONGOING);
        User teacher = userSupplier.getWithRole(User.Role.TEACHER);
        teacher.setId(teacher.getId() + 1);
        return course;
    }

    @Override
    public Course getInvalidEntity() {
        return new Course();
    }
}
