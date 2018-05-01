package by.bsuir.recourse.util;

import by.bsuir.recourse.entity.dto.ErrorMessage;
import by.bsuir.recourse.service.exception.ServiceConstraintViolationException;
import by.bsuir.recourse.service.exception.ServiceException;
import by.bsuir.recourse.util.WrapperFunctions.WrapperFunction;
import by.bsuir.recourse.util.WrapperFunctions.WrapperVoidFunction;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

public class RepositoryCallWrapper {

    public static <R> R wrapJPACall(WrapperFunction<R> function) {
        try {
            return function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static void wrapJPACall(WrapperVoidFunction function) {
        try {
            function.call();
        } catch (DataAccessException e) {
            throw new ServiceException(e);
        }
    }

    public static <T> Optional<T> wrapJPACallToOptional(WrapperFunction<T> function) {
        return wrapJPACall(() -> {
            Optional<T> result;
            try {
                result = Optional.ofNullable(function.call());
            } catch (EmptyResultDataAccessException e) {
                result = Optional.empty();
            } catch (DataIntegrityViolationException e) {
                throw new ServiceConstraintViolationException(new ErrorMessage("Invalid entity", "Invalid entity references"));
            }
            return result;
        });
    }

    public static Optional<Boolean> wrapJPACallToBoolean(WrapperVoidFunction function) {
        return wrapJPACall(() -> {
            Optional<Boolean> result = Optional.of(true);
            try {
                function.call();
            } catch (EmptyResultDataAccessException e) {
                result = Optional.empty();
            } catch (DataIntegrityViolationException e) {
                throw new ServiceConstraintViolationException(new ErrorMessage("Invalid entity", "Invalid entity references"));
            }
            return result;
        });
    }

}
