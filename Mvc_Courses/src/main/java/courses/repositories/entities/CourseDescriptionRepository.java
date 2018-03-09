package courses.repositories.entities;

import courses.models.entities.CourseDescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDescriptionRepository extends JpaRepository<CourseDescription, Long> {
}
