package by.bsuir.recourse.service.impl;

import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.repository.CourseRepository;
import by.bsuir.recourse.repository.LessonRepository;
import by.bsuir.recourse.repository.UserRepository;
import by.bsuir.recourse.service.LessonService;
import by.bsuir.recourse.service.exception.ServiceException;
import by.bsuir.recourse.validation.support.UserFieldInfo;
import by.bsuir.recourse.validation.validator.LessonTimeValidator;
import by.bsuir.recourse.validation.validator.UserRoleValidator;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.bsuir.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.bsuir.recourse.util.Util.ifExistsWithRole;
import static org.slf4j.LoggerFactory.getLogger;

public class LessonServiceImpl
        extends AbstractCrudService<Lesson, Integer>
        implements LessonService {

    private static final Logger logger = getLogger(LessonServiceImpl.class);

    private final LessonRepository repository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private LessonTimeValidator lessonTimeValidator;

    public LessonServiceImpl(LessonRepository repository, CourseRepository courseRepository, UserRepository userRepository, LessonTimeValidator lessonTimeValidator) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.lessonTimeValidator = lessonTimeValidator;
    }

    @Override
    public Optional<Lesson> update(Lesson entity, Integer integer) {
        logger.warn("This method shouldn't be called.");
        throw new ServiceException();
    }

    public Optional<Lesson> update(Lesson entity, Integer id, User.Role performerRole) {
        Optional<Lesson> result;
        Optional<Lesson> databaseLessonOptional = wrapJPACallToOptional(() -> repository.findOne(id));
        if (databaseLessonOptional.isPresent()) {
            entity.setId(id);
            validateEntity(entity);
            if (performerRole == User.Role.TEACHER){
                String hometask = entity.getTask();
                entity = databaseLessonOptional.get();
                entity.setTask(hometask);
            }
            Lesson finalEntity = entity;
            result = wrapJPACallToOptional(() -> repository.save(finalEntity));
        } else {
            result = Optional.empty();
        }
        return result;
    }

    @Override
    public Optional<List<Lesson>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? repository.findByCourseIdOrderByStartTimeDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Lesson>> findByTeacherId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.TEACHER))
                ? repository.findByTeacherIdOrderByStartTimeDesc(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<Lesson>> findByTeacherIdAndCourseId(Integer teacherId, Integer courseId, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(courseId) && ifExistsWithRole(userRepository, teacherId, User.Role.TEACHER))
                ? repository.findByTeacherIdAndCourseIdOrderByStartTimeDesc(teacherId, courseId, pageable)
                : null
        );
    }

    @Override
    protected String getEntityName() {
        return "lesson";
    }

    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<Lesson, Integer> studentFieldInfo = new UserFieldInfo<>(
                Lesson::getTeacher,
                "teacher",
                User.Role.TEACHER
        );
        return Arrays.asList(
                new UserRoleValidator<>(studentFieldInfo, userRepository, repository),
                lessonTimeValidator
        );
    }
}
