package courses.services.entites.impl;

import courses.models.entities.Course;
import courses.repositories.entities.CourseRepository;
import courses.services.entites.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CourseServiceJpaImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.getOne(id);
    }

    @Override
    public Course create(Course model) {
        return courseRepository.save(model);
    }

    @Override
    public Course edit(Course model) {
        return courseRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        courseRepository.delete(id);
    }
}