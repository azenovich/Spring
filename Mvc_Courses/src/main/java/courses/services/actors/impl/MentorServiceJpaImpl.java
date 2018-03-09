package courses.services.actors.impl;

import courses.models.actors.Mentor;
import courses.repositories.actors.MentorRepository;
import courses.services.actors.MentorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class MentorServiceJpaImpl implements MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    @Override
    public List<Mentor> findAll() {
        return mentorRepository.findAll();
    }

    @Override
    public Mentor findById(Long id) {
        return mentorRepository.getOne(id);
    }

    @Override
    public Mentor create(Mentor model) {
        return mentorRepository.save(model);
    }

    @Override
    public Mentor edit(Mentor model) {
        return mentorRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        mentorRepository.delete(id);
    }
}
