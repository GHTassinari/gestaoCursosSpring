package br.com.guilhermetassinari.gestao_cursos.modules.cursos.enums;

import br.com.guilhermetassinari.gestao_cursos.modules.cursos.deserializers.CourseStatusDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CourseStatusDeserializer.class)
public enum CourseStatus {
    ACTIVE,
    INACTIVE
}
