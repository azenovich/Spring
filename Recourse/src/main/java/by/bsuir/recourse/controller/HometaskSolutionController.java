package by.bsuir.recourse.controller;

import by.bsuir.recourse.configuration.security.Auth;
import by.bsuir.recourse.configuration.security.UserAuthDetails;
import by.bsuir.recourse.entity.model.HometaskSolution;
import by.bsuir.recourse.entity.model.Mark;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hometasks/solutions")
public interface HometaskSolutionController extends CrudController<HometaskSolution, Integer> {

    @GetMapping("{solutionId}/mark")
    Mark getMark(@PathVariable("solutionId") Integer solutionId);


    @GetMapping(value = "/student/{studentId}", params = "lessonId")
    HometaskSolution getStudentSolution(
            @PathVariable("studentId") Integer studentId,
            @RequestParam(value = "lessonId", required = false) Integer lessonId,
            @Auth UserAuthDetails authDetails);

    @GetMapping("/student/{studentId}")
    List<HometaskSolution> getStudentSolutions(
            @PathVariable("studentId") Integer studentId,
            @Auth UserAuthDetails authDetails,
            Pageable pageable);

    @GetMapping("/lesson/{lessonId}")
    List<HometaskSolution> getLessonSolutions(
            @PathVariable("lessonId") Integer lessonId,
            @Auth UserAuthDetails authDetails,
            Pageable pageable);

}
