package by.bsuir.recourse.service;

import by.bsuir.recourse.entity.model.BaseEntity;
import by.bsuir.recourse.service.exception.ServiceException;

import java.util.Optional;

public interface CrudService<E extends BaseEntity<ID>, ID> {

    Optional<E> findById(ID id) throws ServiceException;

    Iterable<E> findAll() throws ServiceException;

    <S extends E> Optional<S> add(S entity) throws ServiceException;

    Optional<E> update(E entity, ID id) throws ServiceException;

    Optional<Boolean> delete(ID id) throws ServiceException;
}
