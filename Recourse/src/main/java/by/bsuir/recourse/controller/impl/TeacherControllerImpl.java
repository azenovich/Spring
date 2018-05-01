package by.bsuir.recourse.controller.impl;

import by.bsuir.recourse.controller.TeacherController;
import by.bsuir.recourse.controller.exception.NotFoundException;
import by.bsuir.recourse.entity.model.Lesson;
import by.bsuir.recourse.service.LessonService;
import org.slf4j.Logger;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

import static by.bsuir.recourse.util.ServiceCallWrapper.wrapServiceCall;
import static org.slf4j.LoggerFactory.getLogger;

public class TeacherControllerImpl implements TeacherController {

    private static final Logger logger = getLogger(TeacherControllerImpl.class);
    private final LessonService lessonService;

    public TeacherControllerImpl(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @Override
    public List<Lesson> getLessons(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            Pageable pageable
    ) {
        return wrapServiceCall(logger, () -> {
            Optional<List<Lesson>> lessons;
            if (courseId == null) {
                lessons = lessonService.findByTeacherId(teacherId, pageable);
            } else {
                lessons = lessonService.findByTeacherIdAndCourseId(teacherId, courseId, pageable);
            }
            return lessons.orElseThrow(NotFoundException::new);
        });
    }

}
