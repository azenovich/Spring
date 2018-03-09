package courses.services.entites.impl;

import courses.models.entities.StatusCourseInvite;
import courses.repositories.entities.StatusCourseInviteRepository;
import courses.services.entites.StatusCourseInviteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class StatusCourseInviteJpaImpl implements StatusCourseInviteService {
    @Autowired
    private StatusCourseInviteRepository statusCourseInviteRepository;

    @Override
    public List<StatusCourseInvite> findAll() {
        return statusCourseInviteRepository.findAll();
    }

    @Override
    public StatusCourseInvite findById(Long id) {
        return statusCourseInviteRepository.getOne(id);
    }

    @Override
    public StatusCourseInvite create(StatusCourseInvite model) {
        return statusCourseInviteRepository.save(model);
    }

    @Override
    public StatusCourseInvite edit(StatusCourseInvite model) {
        return statusCourseInviteRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        statusCourseInviteRepository.delete(id);
    }
}