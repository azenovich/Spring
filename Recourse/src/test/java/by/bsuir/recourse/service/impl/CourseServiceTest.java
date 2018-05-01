package by.bsuir.recourse.service.impl;

import by.bsuir.recourse.entity.model.Course;
import by.bsuir.recourse.repository.CourseRepository;
import by.bsuir.recourse.repository.HometaskSolutionRepository;
import by.bsuir.recourse.repository.LessonRepository;
import by.bsuir.recourse.repository.UserRepository;
import by.bsuir.recourse.service.CourseService;
import by.bsuir.recourse.service.CrudService;
import by.bsuir.recourse.service.CrudServiceTest;
import by.bsuir.recourse.supplier.entity.model.EntitySupplier;
import by.bsuir.recourse.supplier.entity.model.impl.CourseSupplier;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.repository.CrudRepository;

import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class CourseServiceTest extends CrudServiceTest<Course, Integer> {
    private CourseService courseService;
    private CourseRepository courseRepository;
    private CourseSupplier courseSupplier;

    public CourseServiceTest() {
        userRepository = Mockito.mock(UserRepository.class);
        courseRepository = Mockito.mock(CourseRepository.class);
        LessonRepository lessonRepository = Mockito.mock(LessonRepository.class);
        HometaskSolutionRepository hometaskSolutionRepository = Mockito.mock(HometaskSolutionRepository.class);
        courseService = new CourseServiceImpl(courseRepository, userRepository, lessonRepository, hometaskSolutionRepository);
        courseSupplier = new CourseSupplier();
    }

    @Override
    protected CrudService<Course, Integer> getCrudService() {
        return courseService;
    }

    @Override
    protected CrudRepository<Course, Integer> getCrudRepository() {
        return courseRepository;
    }

    @Override
    protected EntitySupplier<Course, Integer> getEntitySupplier() {
        return courseSupplier;
    }

    @Test
    public void searchByValidTitleTest() throws Exception {
        when(courseRepository.findByTitleContainingIgnoreCaseOrderByIdDesc(anyString(), any()))
                .thenReturn(Collections.singletonList(courseSupplier.getValidEntityWithId()));

        List<Course> courses = courseRepository.findByTitleContainingIgnoreCaseOrderByIdDesc("test", null);

        verify(courseRepository, times(1))
                .findByTitleContainingIgnoreCaseOrderByIdDesc(anyString(), any());
        assertEquals(1, courses.size());
    }

    @Test
    public void findByStatusTest() throws Exception {
        when(courseRepository.findByStatusOrderByIdDesc(any(), any()))
                .thenReturn(Collections.emptyList());

        courseService.findByStatus(Course.Status.FINISHED, null);

        verify(courseRepository, times(1))
                .findByStatusOrderByIdDesc(any(), any());
    }

    @Override
    protected void setupAllowedRoles(Course entity) { }

}
