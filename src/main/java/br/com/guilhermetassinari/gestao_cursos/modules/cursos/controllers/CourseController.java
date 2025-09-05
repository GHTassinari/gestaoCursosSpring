package br.com.guilhermetassinari.gestao_cursos.modules.cursos.controllers;

import br.com.guilhermetassinari.gestao_cursos.modules.cursos.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.cursos.useCases.CreateCourseUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CreateCourseUseCase createCourseUseCase;

    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody CourseEntity courseEntity) {
        var result = this.createCourseUseCase.execute(courseEntity);
        return ResponseEntity.ok().body(result);
    }

}
