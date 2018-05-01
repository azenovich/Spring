package by.bsuir.recourse.controller;

import by.bsuir.recourse.entity.model.Mark;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hometasks/solutions/marks")
public interface MarkController extends CrudController<Mark, Integer> {
}
