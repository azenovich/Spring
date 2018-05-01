package by.bsuir.recourse.validation.validator;


import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.repository.LessonRepository;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static by.bsuir.recourse.util.RepositoryCallWrapper.wrapJPACall;

public class LessonTimeValidator implements Validator {

    private LessonRepository lessonRepository;

    public LessonTimeValidator(LessonRepository lessonRepository){
        this.lessonRepository = lessonRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Lesson.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Lesson newLesson = (Lesson) o;
        if (newLesson.getId() ==  null){
            if (!wrapJPACall(() -> lessonRepository.canAddLesson(newLesson.getStartTime(), newLesson.getTeacher().getId(), newLesson.getDuration()))) {
                reject(errors);
            }
        } else {
            Lesson oldLesson = wrapJPACall(() -> lessonRepository.findOne(newLesson.getId()));
            if (shouldCheckUpdating(newLesson, oldLesson)){
                if (!lessonRepository.canUpdateLesson(
                        newLesson.getStartTime(),
                        newLesson.getTeacher().getId(),
                        newLesson.getDuration(),
                        newLesson.getId())
                    ) {
                    reject(errors);
                }
            }
        }
    }

    private void reject(Errors errors){
        errors.rejectValue("teacher", "Teacher is already busy for requested time.");
    }

    private boolean shouldCheckUpdating(Lesson oldLesson, Lesson newLesson) {
        return  !oldLesson.getStartTime().equals(newLesson.getStartTime()) ||
                !oldLesson.getDuration().equals(newLesson.getDuration()) ||
                !oldLesson.getTeacher().getId().equals(newLesson.getTeacher().getId());
    }
}
