package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.configuration.security.Auth;
import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.controller.CourseController;
import by.bsuir.recourse.controller.exception.AccessDeniedException;
import by.bsuir.recourse.controller.exception.NotFoundException;
import by.bsuir.recourse.entity.model.Course;
import by.bsuir.recourse.entity.model.CourseFeedback;
import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.entity.support.CourseStatusEnumConverter;
import by.bsuir.recourse.service.CourseFeedbackService;
import by.bsuir.recourse.service.CourseService;
import by.bsuir.recourse.service.LessonService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static by.bsuir.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class CourseControllerImpl
        extends AbstractCrudController<Course, Integer>
        implements CourseController {

    private static final Logger logger = getLogger(CourseControllerImpl.class);
    private final CourseService courseService;
    private final LessonService lessonService;
    private final CourseFeedbackService courseFeedbackService;

    public CourseControllerImpl(CourseService courseService,
                                LessonService lessonService,
                                CourseFeedbackService courseFeedbackService
    ) {
        super(courseService, logger);
        this.courseService = courseService;
        this.lessonService = lessonService;
        this.courseFeedbackService = courseFeedbackService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Course.Status.class,
                new CourseStatusEnumConverter());
    }

    @Override
    public List<Lesson> getLessons(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<Lesson>> lessons = lessonService.findByCourseId(courseId, pageable);
            return lessons.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<CourseFeedback> getFeedbacks(@PathVariable("courseId") Integer courseId, Pageable pageable) {
        return wrapServiceCall(logger, () -> {
            Optional<List<CourseFeedback>> feedbacks = courseFeedbackService.findByCourseId(courseId, pageable);
            return feedbacks.orElseThrow(NotFoundException::new);
        });
    }

    @Override
    public List<User> getStudents(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        if (authDetails.isAdmin()) {
            return wrapServiceCall(logger, () -> courseService.findStudentsForCourse(courseId));
        } else {
            throw new AccessDeniedException();
        }
    }

    @Override
    public List<Course> searchByTitle(@RequestParam("title") String title, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.searchByTitle(title, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Course> searchByStatus(@RequestParam("status") Course.Status status, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findByStatus(status, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Course> getAvailableForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findAvailableForUser(studentId, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Course> getRegisteredForStudent(@PathVariable("studentId") Integer studentId, Pageable pageable) {
        return wrapServiceCall(logger, () -> courseService.findRegisteredForUser(studentId, pageable))
                .orElseThrow(NotFoundException::new);
    }

    @Override
    protected boolean hasAuthorityToEdit(Course entity, UserAuthDetails authDetails) {
        return authDetails.getRole() == User.Role.ADMIN;
    }

    public void registerToCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        wrapServiceCall(logger, () -> courseService.registerStudentToCourse(courseId, authDetails.getId(), false));
    }

    @Override
    public void unregisterFromCourse(@PathVariable("courseId") Integer courseId, @Auth UserAuthDetails authDetails) {
        wrapServiceCall(logger, () -> courseService.removeStudentFromCourse(courseId, authDetails.getId(), false));
    }

    @Override
    public void registerStudentToCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> courseService.registerStudentToCourse(courseId, studentId, true));
    }

    @Override
    public void unregisterStudentFromCourse(@PathVariable("courseId") Integer courseId, @RequestParam("studentId") Integer studentId, @Auth UserAuthDetails authDetails) {
        checkAuthority(null, authDetails, this::hasAuthorityToEdit);
        wrapServiceCall(logger, () -> courseService.removeStudentFromCourse(courseId, studentId, true));
    }
}
