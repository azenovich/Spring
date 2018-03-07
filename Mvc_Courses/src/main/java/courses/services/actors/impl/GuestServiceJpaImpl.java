package courses.services.actors.impl;

import courses.models.actors.Guest;
import courses.repositories.actors.GuestRepository;
import courses.services.actors.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class GuestServiceJpaImpl implements GuestService {
    @Autowired
    private GuestRepository guestRepository;

    @Override
    public List<Guest> findAll() {
        return guestRepository.findAll();
    }

    @Override
    public Guest findById(Long id) {
        return guestRepository.getOne(id);
    }

    @Override
    public Guest create(Guest model) {
        return guestRepository.save(model);
    }

    @Override
    public Guest edit(Guest model) {
        return guestRepository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        guestRepository.delete(id);
    }
}
