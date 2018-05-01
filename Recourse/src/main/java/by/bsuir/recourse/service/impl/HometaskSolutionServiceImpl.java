package by.bsuir.recourse.service.impl;

import by.bsuir.recourse.entity.model.HometaskSolution;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.repository.CourseRepository;
import by.bsuir.recourse.repository.HometaskSolutionRepository;
import by.bsuir.recourse.repository.LessonRepository;
import by.bsuir.recourse.repository.UserRepository;
import by.bsuir.recourse.service.HometaskSolutionService;
import by.bsuir.recourse.validation.support.UserFieldInfo;
import by.bsuir.recourse.validation.validator.HometaskSolutionReferenceValidator;
import by.bsuir.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static by.bsuir.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;
import static by.bsuir.recourse.util.Util.ifExistsWithRole;

public class HometaskSolutionServiceImpl
        extends AbstractCrudService<HometaskSolution, Integer>
        implements HometaskSolutionService {

    private final HometaskSolutionRepository repository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private HometaskSolutionReferenceValidator hometaskSolutionReferenceValidator;
    private LessonRepository lessonRepository;

    public HometaskSolutionServiceImpl(HometaskSolutionRepository repository,
                                       UserRepository userRepository,
                                       LessonRepository lessonRepository,
                                       CourseRepository courseRepository,
                                       HometaskSolutionReferenceValidator hometaskSolutionReferenceValidator) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.lessonRepository = lessonRepository;
        this.courseRepository = courseRepository;
        this.hometaskSolutionReferenceValidator = hometaskSolutionReferenceValidator;
    }


    @Override
    public Optional<HometaskSolution> update(HometaskSolution entity, Integer integer) {

        return super.update(entity, integer);
    }

    @Override
    public Optional<List<HometaskSolution>> findByLessonId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (lessonRepository.exists(id))
                ? repository.findByLessonId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<List<HometaskSolution>> findByStudentId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, id, User.Role.STUDENT))
                ? repository.findByStudentId(id, pageable)
                : null
        );
    }

    @Override
    public Optional<HometaskSolution> findByStudentIdAndLessonId(Integer studentId, Integer lessonId) {
        return wrapJPACallToOptional(() -> (ifExistsWithRole(userRepository, studentId, User.Role.STUDENT) && lessonRepository.exists(lessonId))
                ? repository.findByStudentIdAndLessonId(studentId, lessonId)
                : null
        );
    }

    @Override
    protected String getEntityName() {
        return "hometask solution";
    }

    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<HometaskSolution, Integer> studentFieldInfo = new UserFieldInfo<>(
                HometaskSolution::getStudent,
                "student",
                User.Role.STUDENT
        );
        return Arrays.asList(
                new UserRoleValidator<>(studentFieldInfo, userRepository, repository),
                hometaskSolutionReferenceValidator);
    }
}
