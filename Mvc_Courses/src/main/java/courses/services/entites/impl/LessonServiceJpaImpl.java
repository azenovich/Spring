package courses.services.entites.impl;

import courses.models.entities.Lesson;
import courses.repositories.entities.LessonRepository;
import courses.services.entites.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class LessonServiceJpaImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Lesson findById(Long id) {
        return lessonRepository.getOne(id);
    }

    @Override
    public Lesson create(Lesson model) {
        return lessonRepository.save(model);
    }

    @Override
    public Lesson edit(Lesson model) {
        return lessonRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        lessonRepository.delete(id);
    }
}