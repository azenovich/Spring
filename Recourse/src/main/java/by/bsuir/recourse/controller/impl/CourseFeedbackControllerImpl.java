package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.CourseFeedbackController;
import by.bsuir.recourse.entity.model.CourseFeedback;
import by.bsuir.recourse.service.CourseFeedbackService;
import org.slf4j.Logger;

import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class CourseFeedbackControllerImpl
        extends AbstractCrudController<CourseFeedback, Integer>
        implements CourseFeedbackController {

    private static final Logger logger = getLogger(CourseFeedbackControllerImpl.class);

    public CourseFeedbackControllerImpl(CourseFeedbackService courseFeedbackService) {
        super(courseFeedbackService, logger);
    }


    @Override
    protected boolean hasAuthorityToEdit(CourseFeedback entity, UserAuthDetails authDetails) {
        return Objects.equals(entity.getStudent().getId(), authDetails.getId());
    }
}
