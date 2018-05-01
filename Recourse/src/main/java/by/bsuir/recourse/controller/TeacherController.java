package by.bsuir.recourse.controller;

import by.bsuir.recourse.entity.model.Lesson;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teachers")
public interface TeacherController {

    @GetMapping("{teacherId}/lessons")
    List<Lesson> getLessons(
            @PathVariable("teacherId") Integer teacherId,
            @RequestParam(value = "courseId", required = false) Integer courseId,
            Pageable pageable
    );

}
