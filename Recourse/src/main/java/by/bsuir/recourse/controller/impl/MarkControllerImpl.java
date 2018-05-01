package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.MarkController;
import by.bsuir.recourse.entity.model.HometaskSolution;
import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.entity.model.Mark;
import by.bsuir.recourse.service.HometaskSolutionService;
import by.bsuir.recourse.service.LessonService;
import by.bsuir.recourse.service.MarkService;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.Optional;

import static org.slf4j.LoggerFactory.getLogger;

public class MarkControllerImpl
        extends AbstractCrudController<Mark, Integer>
        implements MarkController {

    private static final Logger logger = getLogger(MarkControllerImpl.class);
    private final HometaskSolutionService hometaskSolutionService;
    private final LessonService lessonService;

    public MarkControllerImpl(
            MarkService markService,
            HometaskSolutionService hometaskSolutionService,
            LessonService lessonService
    ) {
        super(markService, logger);
        this.hometaskSolutionService = hometaskSolutionService;
        this.lessonService = lessonService;
    }

    @Override
    protected boolean hasAuthorityToEdit(Mark entity, UserAuthDetails authDetails) {
        Optional<HometaskSolution> hometaskSolution = hometaskSolutionService.findById(entity.getSolutionId());
        if (hometaskSolution.isPresent()) {
            Optional<Lesson> lesson = lessonService.findById(hometaskSolution.get().getLessonId());
            if (lesson.isPresent()) {
                return Objects.equals(lesson.get().getTeacher().getId(), authDetails.getId());
            }
        }
        return false;
    }
}
