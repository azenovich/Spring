package by.bsuir.recourse.repository;

import by.bsuir.recourse.entity.model.Mark;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarkRepository extends PagingAndSortingRepository<Mark, Integer> {

    Mark findBySolutionId(Integer id);

}
