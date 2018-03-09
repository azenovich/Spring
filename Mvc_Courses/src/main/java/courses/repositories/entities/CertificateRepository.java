package courses.repositories.entities;

import courses.models.entities.Certificate;
import courses.models.entities.CertificateKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate, CertificateKey> {
}
