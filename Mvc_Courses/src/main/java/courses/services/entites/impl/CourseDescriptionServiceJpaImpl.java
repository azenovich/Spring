package courses.services.entites.impl;

import courses.models.entities.CourseDescription;
import courses.repositories.entities.CourseDescriptionRepository;
import courses.services.entites.CourseDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CourseDescriptionServiceJpaImpl implements CourseDescriptionService {
    @Autowired
    private CourseDescriptionRepository courseDescriptionRepository;

    @Override
    public List<CourseDescription> findAll() {
        return courseDescriptionRepository.findAll();
    }

    @Override
    public CourseDescription findById(Long id) {
        return courseDescriptionRepository.getOne(id);
    }

    @Override
    public CourseDescription create(CourseDescription model) {
        return courseDescriptionRepository.save(model);
    }

    @Override
    public CourseDescription edit(CourseDescription model) {
        return courseDescriptionRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        courseDescriptionRepository.delete(id);
    }
}