package courses.services.entites.impl;

import courses.models.entities.Certificate;
import courses.models.entities.CertificateKey;
import courses.repositories.entities.CertificateRepository;
import courses.services.entites.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class CertificateServiceJpaImpl implements CertificateService {
    @Autowired
    private CertificateRepository certificateRepository;

    @Override
    public List<Certificate> findAll() {
        return certificateRepository.findAll();
    }

    @Override
    public Certificate findById(CertificateKey id) {
        return certificateRepository.getOne(id);
    }

    @Override
    public Certificate create(Certificate model) {
        return certificateRepository.save(model);
    }

    @Override
    public Certificate edit(Certificate model) {
        return certificateRepository.save(model);
    }

    @Override
    public void deleteById(CertificateKey id) {
        certificateRepository.delete(id);
    }
}