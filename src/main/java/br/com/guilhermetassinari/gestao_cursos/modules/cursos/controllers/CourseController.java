package br.com.guilhermetassinari.gestao_cursos.modules.cursos.controllers;

import br.com.guilhermetassinari.gestao_cursos.modules.cursos.dto.CreateCourseDTO;
import br.com.guilhermetassinari.gestao_cursos.modules.cursos.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.cursos.useCases.CreateCourseUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CreateCourseUseCase createCourseUseCase;

    @PostMapping("")
    public ResponseEntity<Void> create(@Valid @RequestBody CreateCourseDTO createCourseDTO) {
        var courseEntity = CourseEntity.builder()
                .name(createCourseDTO.getName())
                .category(createCourseDTO.getCategory())
                .status(createCourseDTO.getStatus())
                .build();

        this.createCourseUseCase.execute(courseEntity);

        return ResponseEntity.ok().build();
    }

    //ResponseEntity<List<CourseEntity>>
    @GetMapping("")
    public void  getCourses(@Valid @RequestBody CourseEntity courseEntity) {
    }

}
