package by.bsuir.recourse.util;

import by.bsuir.recourse.controller.exception.ControllerException;
import by.bsuir.recourse.controller.exception.RequestException;
import by.bsuir.recourse.service.exception.ServiceException;
import by.bsuir.recourse.util.WrapperFunctions.WrapperFunction;
import by.bsuir.recourse.util.WrapperFunctions.WrapperVoidFunction;
import by.bsuir.recourse.validation.exception.ServiceRequestException;
import org.slf4j.Logger;

public class ServiceCallWrapper {

    public static <R> R wrapServiceCall(Logger logger, WrapperFunction<R> function) {
        try {
            return function.call();
        } catch (ServiceRequestException e) {
            logger.trace("Fail to call service because of bad request ", e);
            throw new RequestException(e.getStatus(), e.getErrorMessages());
        } catch (ServiceException e) {
            logger.warn("Fail to call service\n", e);
            throw new ControllerException(e);
        }
    }

    public static void wrapServiceCall(Logger logger, WrapperVoidFunction function) {
        wrapServiceCall(logger, () -> {
            function.call();
            return null;
        });
    }

}
