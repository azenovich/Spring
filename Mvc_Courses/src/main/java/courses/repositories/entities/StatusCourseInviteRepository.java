package courses.repositories.entities;

import courses.models.entities.StatusCourseInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusCourseInviteRepository extends JpaRepository<StatusCourseInvite, Long> {
}
