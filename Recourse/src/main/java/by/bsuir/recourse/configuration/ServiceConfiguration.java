package by.bsuir.recourse.configuration;

import by.bsuir.recourse.repository.*;
import by.bsuir.recourse.service.*;
import by.bsuir.recourse.service.impl.*;
import by.bsuir.recourse.validation.validator.HometaskSolutionReferenceValidator;
import by.bsuir.recourse.validation.validator.LessonTimeValidator;
import by.bsuir.recourse.validation.validator.PasswordChangingValidator;
import by.bsuir.recourse.validation.validator.RegistrationDetailsValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Configuration
public class ServiceConfiguration {

    @Bean
    public UserService userService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            RegistrationDetailsValidator registrationDetailsValidator,
            LessonRepository lessonRepository,
            TokenStore tokenStore,
            ConsumerTokenServices consumerTokenServices,
            PasswordChangingValidator passwordChangingValidator) {
        return new UserServiceImpl(
                userRepository,
                lessonRepository,
                passwordEncoder,
                registrationDetailsValidator,
                tokenStore,
                consumerTokenServices,
                passwordChangingValidator);
    }

    @Bean
    public CourseService courseService(
            CourseRepository courseRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository,
            HometaskSolutionRepository hometaskSolutionRepository) {
        return new CourseServiceImpl(courseRepository, userRepository, lessonRepository, hometaskSolutionRepository);
    }

    @Bean
    public CourseFeedbackService courseFeedbackService(
            CourseFeedbackRepository courseFeedbackRepository,
            CourseRepository courseRepository,
            UserRepository userRepository) {
        return new CourseFeedbackServiceImpl(courseFeedbackRepository, courseRepository, userRepository);
    }

    @Bean
    public HometaskSolutionService hometaskSolutionService(
            HometaskSolutionRepository hometaskSolutionRepository,
            UserRepository userRepository,
            LessonRepository lessonRepository,
            CourseRepository courseRepository,
            HometaskSolutionReferenceValidator hometaskSolutionReferenceValidator) {
        return new HometaskSolutionServiceImpl(hometaskSolutionRepository, userRepository, lessonRepository, courseRepository, hometaskSolutionReferenceValidator);
    }

    @Bean
    public LessonService lessonService(
            LessonRepository lessonRepository,
            CourseRepository courseRepository,
            UserRepository userRepository,
            LessonTimeValidator lessonTimeValidator) {
        return new LessonServiceImpl(lessonRepository, courseRepository, userRepository, lessonTimeValidator);
    }

    @Bean
    public MarkService markService(MarkRepository markRepository) {
        return new MarkServiceImpl(markRepository);
    }


}
