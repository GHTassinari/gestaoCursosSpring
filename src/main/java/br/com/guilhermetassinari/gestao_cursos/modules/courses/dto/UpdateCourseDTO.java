package br.com.guilhermetassinari.gestao_cursos.modules.courses.dto;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = false)
public class UpdateCourseDTO {
    private String name;
    private String category;
}
