package courses.services.actors.impl;

import courses.models.actors.Student;
import courses.repositories.actors.StudentRepository;
import courses.services.actors.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class StudentServiceJpaImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.getOne(id);
    }

    @Override
    public Student create(Student model) {
        return studentRepository.save(model);
    }

    @Override
    public Student edit(Student model) {
        return studentRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.delete(id);
    }
}
