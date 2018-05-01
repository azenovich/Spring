package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.controller.CourseFeedbackController;
import by.bsuir.recourse.controller.CrudController;
import by.bsuir.recourse.controller.CrudControllerTest;
import by.bsuir.recourse.entity.model.CourseFeedback;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.service.CourseFeedbackService;
import by.bsuir.recourse.service.CrudService;
import by.bsuir.recourse.supplier.entity.model.EntitySupplier;
import by.bsuir.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
import org.mockito.Mockito;

public class CourseFeedbackControllerTest extends CrudControllerTest<CourseFeedback, Integer> {

    private CourseFeedbackController courseFeedbackController;
    private CourseFeedbackService courseFeedbackService;
    private CourseFeedbackSupplier courseFeedbackSupplier;

    public CourseFeedbackControllerTest() {
        courseFeedbackService = Mockito.mock(CourseFeedbackService.class);
        courseFeedbackController = new CourseFeedbackControllerImpl(courseFeedbackService);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
    }

    @Override
    protected CrudController<CourseFeedback, Integer> getController() {
        return courseFeedbackController;
    }

    @Override
    protected CrudService<CourseFeedback, Integer> getService() {
        return courseFeedbackService;
    }

    @Override
    protected String getEntityName() {
        return "courses/feedbacks";
    }

    @Override
    protected EntitySupplier<CourseFeedback, Integer> getEntitySupplier() {
        return courseFeedbackSupplier;
    }

    @Override
    protected User prepareAuthorizedUser(CourseFeedback entity, User validUserWithId) {
        validUserWithId.setId(entity.getStudent().getId());
        return validUserWithId;
    }
}
