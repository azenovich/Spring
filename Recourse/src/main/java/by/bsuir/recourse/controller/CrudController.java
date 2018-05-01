package by.bsuir.recourse.controller;

import by.bsuir.recourse.configuration.security.Auth;
import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.exception.ControllerException;
import by.bsuir.recourse.entity.model.BaseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface CrudController<E extends BaseEntity<ID>, ID> {

    @GetMapping("{id}")
    E getById(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) throws ControllerException;

    @GetMapping
    Iterable<E> getAll(@Auth UserAuthDetails authDetails) throws ControllerException;

    @PostMapping
    <S extends E> S create(@RequestBody S entity, @Auth UserAuthDetails authDetails) throws ControllerException;

    @PutMapping("{id}")
    E update(@RequestBody E entity, @PathVariable("id") ID id, @Auth UserAuthDetails authDetails) throws ControllerException;

    @DeleteMapping("{id}")
    void delete(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) throws ControllerException;

}
