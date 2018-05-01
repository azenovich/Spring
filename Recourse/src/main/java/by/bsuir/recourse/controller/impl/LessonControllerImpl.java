package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.configuration.security.Auth;
import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.LessonController;
import by.bsuir.recourse.controller.exception.NotFoundException;
import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.service.LessonService;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

import static by.bsuir.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class LessonControllerImpl
        extends AbstractCrudController<Lesson, Integer>
        implements LessonController {

    private static final Logger logger = getLogger(LessonControllerImpl.class);
    private LessonService lessonService;

    public LessonControllerImpl(LessonService lessonService) {
        super(lessonService, logger);
        this.lessonService = lessonService;
    }

    @Override
    public Lesson update(@Valid @RequestBody Lesson entity, @PathVariable("id") Integer id, @Auth UserAuthDetails authDetails) {
        checkAuthority(entity, authDetails, this::hasAuthorityToEdit);
        return wrapServiceCall(logger, () -> {
            Optional<Lesson> callResult = lessonService.update(entity, id, authDetails.getRole());
            return callResult.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    protected boolean hasAuthorityToEdit(Lesson entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getTeacher().getId(), authDetails.getId());
    }
}
