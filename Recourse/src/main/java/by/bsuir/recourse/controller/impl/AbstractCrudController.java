package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.configuration.security.Auth;
import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.CrudController;
import by.bsuir.recourse.controller.exception.AccessDeniedException;
import by.bsuir.recourse.controller.exception.BadRequestException;
import by.bsuir.recourse.controller.exception.NotFoundException;
import by.bsuir.recourse.entity.model.BaseEntity;
import by.bsuir.recourse.service.CrudService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;
import java.util.function.BiFunction;

import static by.bsuir.recourse.util.ServiceCallWrapper.wrapServiceCall;

public abstract class AbstractCrudController<E extends BaseEntity<ID>, ID> implements CrudController<E, ID> {

    private final Logger logger;
    private final CrudService<E, ID> crudService;

    AbstractCrudController(CrudService<E, ID> crudService, Logger logger) {
        this.logger = logger;
        this.crudService = crudService;
    }

    protected abstract boolean hasAuthorityToEdit(E entity, UserAuthDetails authDetails);

    protected boolean hasAuthorityToRead(E entity, UserAuthDetails authDetails) {
        return true;
    }

    protected void checkAuthority(E entity, UserAuthDetails authDetails, BiFunction<E, UserAuthDetails, Boolean> authorityChecker) {
        if (!authDetails.isAdmin() && !authorityChecker.apply(entity, authDetails)) {
            throw new AccessDeniedException();
        }
    }

    @Override
    public E getById(@PathVariable("id") ID id, @Auth UserAuthDetails userAuthDetails) {
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.findById(id);
            if (callResult.isPresent()) {
                checkAuthority(callResult.get(), userAuthDetails, this::hasAuthorityToRead);
                return callResult.get();
            } else {
                throw new NotFoundException();
            }
        });
    }

    public Iterable<E> getAll(@Auth UserAuthDetails authDetails) {
        return wrapServiceCall(logger, crudService::findAll);
    }

    @Override
    public <S extends E> S create(@Valid @RequestBody S entity, @Auth UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<S> callResult = crudService.add(entity);
            return callResult.orElseThrow(BadRequestException::new);
        });
    }

    @Override
    public E update(@Valid @RequestBody E entity, @PathVariable("id") ID id, @Auth UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<E> callResult = crudService.update(entity, id);
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public void delete(@PathVariable("id") ID id, @Auth UserAuthDetails authDetails) {
        checkAuthority(getById(id, authDetails), authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> crudService.delete(id).orElseThrow(NotFoundException::new));
    }
}
