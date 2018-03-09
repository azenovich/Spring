package courses.repositories.entities;

import courses.models.entities.CourseInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseInviteRepository extends JpaRepository<CourseInvite, Long> {
}
