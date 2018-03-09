package courses.services.entites.impl;

import courses.models.entities.CourseInvite;
import courses.repositories.entities.CourseInviteRepository;
import courses.services.entites.CourseInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CourseInviteServiceJpaImpl implements CourseInviteService {
    @Autowired
    private CourseInviteRepository courseInviteRepository;

    @Override
    public List<CourseInvite> findAll() {
        return courseInviteRepository.findAll();
    }

    @Override
    public CourseInvite findById(Long id) {
        return courseInviteRepository.getOne(id);
    }

    @Override
    public CourseInvite create(CourseInvite model) {
        return courseInviteRepository.save(model);
    }

    @Override
    public CourseInvite edit(CourseInvite model) {
        return courseInviteRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        courseInviteRepository.delete(id);
    }
}