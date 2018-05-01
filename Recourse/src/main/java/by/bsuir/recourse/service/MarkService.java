package by.bsuir.recourse.service;

import by.bsuir.recourse.entity.model.Mark;

import java.util.Optional;

public interface MarkService extends CrudService<Mark, Integer> {

    Optional<Mark> findBySolutionId(Integer id);

}
