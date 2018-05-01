package by.bsuir.recourse.service.impl;

import by.bsuir.recourse.entity.model.CourseFeedback;
import by.bsuir.recourse.entity.model.User;
import by.bsuir.recourse.repository.CourseFeedbackRepository;
import by.bsuir.recourse.repository.CourseRepository;
import by.bsuir.recourse.repository.UserRepository;
import by.bsuir.recourse.service.CourseFeedbackService;
import by.bsuir.recourse.validation.support.UserFieldInfo;
import by.bsuir.recourse.validation.validator.UserRoleValidator;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.bsuir.recourse.util.RepositoryCallWrapper.wrapJPACallToOptional;

public class CourseFeedbackServiceImpl
        extends AbstractCrudService<CourseFeedback, Integer>
        implements CourseFeedbackService {

    private final CourseFeedbackRepository repository;
    private final CourseRepository courseRepository;
    private UserRepository userRepository;

    public CourseFeedbackServiceImpl(CourseFeedbackRepository repository, CourseRepository courseRepository, UserRepository userRepository) {
        super(repository);
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<List<CourseFeedback>> findByCourseId(Integer id, Pageable pageable) {
        return wrapJPACallToOptional(() -> (courseRepository.exists(id))
                ? repository.findByCourseIdOrderByIdDesc(id, pageable)
                : null
        );
    }

    @Override
    protected String getEntityName() {
        return "course feedback";
    }


    @Override
    protected List<Validator> getValidators() {
        UserFieldInfo<CourseFeedback, Integer> studentFieldInfo = new UserFieldInfo<>(
                CourseFeedback::getStudent,
                "student",
                User.Role.STUDENT
        );
        return Collections.singletonList(new UserRoleValidator<>(studentFieldInfo, userRepository, repository));
    }
}
