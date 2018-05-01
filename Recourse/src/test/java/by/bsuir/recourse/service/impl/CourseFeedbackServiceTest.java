package by.bsuir.recourse.service.impl;

import by.bsuir.recourse.entity.model.CourseFeedback;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.repository.CourseFeedbackRepository;
import by.bsuir.recourse.repository.CourseRepository;
import by.bsuir.recourse.service.CourseFeedbackService;
import by.bsuir.recourse.service.CrudService;
import by.bsuir.recourse.service.CrudServiceTest;
import by.bsuir.recourse.supplier.entity.model.EntitySupplier;
import by.bsuir.recourse.supplier.entity.model.impl.CourseFeedbackSupplier;
import by.bsuir.recourse.supplier.entity.model.impl.UserSupplier;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;

public class CourseFeedbackServiceTest extends CrudServiceTest<CourseFeedback, Integer> {
    private CourseFeedbackService courseFeedbackService;
    private CourseFeedbackRepository courseFeedbackRepository;
    private CourseFeedbackSupplier courseFeedbackSupplier;
    private CourseRepository courseRepository;
    private UserSupplier userSupplier;

    public CourseFeedbackServiceTest() {
        courseRepository = Mockito.mock(CourseRepository.class);
        courseFeedbackRepository = Mockito.mock(CourseFeedbackRepository.class);
        courseFeedbackService = new CourseFeedbackServiceImpl(courseFeedbackRepository, courseRepository, userRepository);
        courseFeedbackSupplier = new CourseFeedbackSupplier();
        userSupplier = new UserSupplier();
    }

    @Test
    public void findByExistingCourseIdTest() throws Exception {
        Integer id = courseFeedbackSupplier.getAnyId();
        when(courseRepository.exists(id)).thenReturn(true);
        when(courseFeedbackRepository.findByCourseIdOrderByIdDesc(eq(id), Matchers.any()))
                .thenReturn(Collections.singletonList(courseFeedbackSupplier.getValidEntityWithId()));

        Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(id, null);

        verify(courseFeedbackRepository, times(1)).findByCourseIdOrderByIdDesc(eq(id), any());
        verify(courseRepository, times(1)).exists(id);
        assertTrue(feedbacks.isPresent());
    }

    @Test
    public void findByNotExistingCourseIdTest() throws Exception {
        Integer id = courseFeedbackSupplier.getAnyId();
        when(courseRepository.exists(id)).thenReturn(false);

        Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(id, null);

        verify(courseRepository, times(1)).exists(id);
        assertFalse(feedbacks.isPresent());
    }

    @Override
    protected CrudService<CourseFeedback, Integer> getCrudService() {
        return courseFeedbackService;
    }

    @Override
    protected CrudRepository<CourseFeedback, Integer> getCrudRepository() {
        return courseFeedbackRepository;
    }

    @Override
    protected EntitySupplier<CourseFeedback, Integer> getEntitySupplier() {
        return courseFeedbackSupplier;
    }

    @Override
    protected void setupAllowedRoles(CourseFeedback entity) {
        Integer studentId = entity.getStudent().getId();
        when(userRepository.findOne(studentId)).thenReturn(userSupplier.getWithRole(User.Role.STUDENT));
    }

}
