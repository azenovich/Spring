package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.controller.CrudController;
import by.bsuir.recourse.controller.CrudControllerTest;
import by.bsuir.recourse.controller.LessonController;
import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.service.CrudService;
import by.bsuir.recourse.service.LessonService;
import by.bsuir.recourse.supplier.entity.model.EntitySupplier;
import by.bsuir.recourse.supplier.entity.model.impl.LessonSupplier;
import org.mockito.Mockito;

import java.util.Optional;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LessonControllerTest extends CrudControllerTest<Lesson, Integer> {

    private LessonController lessonController;
    private LessonService lessonService;
    private LessonSupplier lessonSupplier;

    public LessonControllerTest() {
        lessonService = Mockito.mock(LessonService.class);
        lessonController = new LessonControllerImpl(lessonService);
        lessonSupplier = new LessonSupplier();
    }

    @Override
    public void updateEntityValidDataTest() throws Exception {
        when(lessonService.update(any(), any(), any())).thenReturn(Optional.of(getEntitySupplier().getValidEntityWithId()));

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isOk());
    }

    @Override
    public void updateNotExistingEntityTest() throws Exception {
        when(lessonService.update(any(), any(), any())).thenReturn(Optional.empty());

        putEntityByIdAuthorized(getEntitySupplier().getAnyId(), getEntitySupplier().getValidEntityWithoutId())
                .andExpect(status().isNotFound());
    }

    @Override
    protected CrudController<Lesson, Integer> getController() {
        return lessonController;
    }

    @Override
    protected CrudService<Lesson, Integer> getService() {
        return lessonService;
    }

    @Override
    protected String getEntityName() {
        return "lessons";
    }

    @Override
    protected EntitySupplier<Lesson, Integer> getEntitySupplier() {
        return lessonSupplier;
    }

    @Override
    protected User prepareAuthorizedUser(Lesson entity, User validUserWithId) {
        validUserWithId.setId(entity.getTeacher().getId());
        return validUserWithId;
    }
}
