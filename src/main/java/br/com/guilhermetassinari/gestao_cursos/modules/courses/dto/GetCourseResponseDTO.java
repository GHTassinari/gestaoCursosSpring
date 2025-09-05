package br.com.guilhermetassinari.gestao_cursos.modules.courses.dto;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetCourseResponseDTO {
    private String name;
    private String category;
    private CourseStatus status;
}