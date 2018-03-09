package courses.services.entites.impl;

import courses.models.entities.Speciality;
import courses.repositories.entities.SpecialityRepository;
import courses.services.entites.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class SpecialityServiceJpaImpl implements SpecialityService {
    @Autowired
    private SpecialityRepository specialityRepository;

    @Override
    public List<Speciality> findAll() {
        return specialityRepository.findAll();
    }

    @Override
    public Speciality findById(Long id) {
        return specialityRepository.getOne(id);
    }

    @Override
    public Speciality create(Speciality model) {
        return specialityRepository.save(model);
    }

    @Override
    public Speciality edit(Speciality model) {
        return specialityRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        specialityRepository.delete(id);
    }
}