package br.com.guilhermetassinari.gestao_cursos.modules.courses.enums;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.deserializers.CourseStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CourseStatusDeserializer.class)
public enum CourseStatus {
    ACTIVE,
    INACTIVE
}
