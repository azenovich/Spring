package by.bsuir.recourse.controller;

import by.bsuir.recourse.entity.model.Lesson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/lessons")
public interface LessonController extends CrudController<Lesson, Integer> { }
