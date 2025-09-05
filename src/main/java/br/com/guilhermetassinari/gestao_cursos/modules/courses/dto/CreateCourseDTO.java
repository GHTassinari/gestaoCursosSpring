package br.com.guilhermetassinari.gestao_cursos.modules.courses.dto;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
// Need to activate it in the application properties to be a bad request whenever inserting more than the required.
@JsonIgnoreProperties(ignoreUnknown = false)
public class CreateCourseDTO {
    private String name;
    private String category;
    private CourseStatus status;
}
