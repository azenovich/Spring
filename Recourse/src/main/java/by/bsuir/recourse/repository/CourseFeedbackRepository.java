package by.bsuir.recourse.repository;

import by.bsuir.recourse.entity.model.CourseFeedback;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CourseFeedbackRepository extends PagingAndSortingRepository<CourseFeedback, Integer> {

    List<CourseFeedback> findByCourseIdOrderByIdDesc(Integer courseId, Pageable pageable);

}