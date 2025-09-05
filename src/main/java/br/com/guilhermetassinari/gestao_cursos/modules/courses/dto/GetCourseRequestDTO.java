package br.com.guilhermetassinari.gestao_cursos.modules.courses.dto;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@JsonIgnoreProperties(ignoreUnknown = false)
public class GetCourseRequestDTO {
    private String id;
    private String name;
    private String category;
    private String status;
    private int page = 0;
    private int size = 10;
}
