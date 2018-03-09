package courses.services.actors.impl;

import courses.models.actors.HR;
import courses.repositories.actors.HRRepository;
import courses.services.actors.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class HRServiceJpaImpl implements HRService {
    @Autowired
    private HRRepository hrRepository;

    @Override
    public List<HR> findAll() {
        return hrRepository.findAll();
    }

    @Override
    public HR findById(Long id) {
        return hrRepository.getOne(id);
    }

    @Override
    public HR create(HR model) {
        return hrRepository.save(model);
    }

    @Override
    public HR edit(HR model) {
        return hrRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        hrRepository.delete(id);
    }
}
