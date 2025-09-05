package br.com.guilhermetassinari.gestao_cursos.modules.cursos.deserializers;

import br.com.guilhermetassinari.gestao_cursos.exceptions.StatusRequiredException;
import br.com.guilhermetassinari.gestao_cursos.modules.cursos.enums.CourseStatus;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CourseStatusDeserializer extends JsonDeserializer<CourseStatus> {
    @Override
    public CourseStatus deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        if (value == null || value.isEmpty()) {
            throw new StatusRequiredException("Status is null or empty");
        }

        if (Arrays.stream(CourseStatus.values()).noneMatch(status -> status.name().equals(value))) {
            String validValues = Arrays.stream(CourseStatus.values()).map(Enum::name).collect(Collectors.joining(" or "));
            throw new StatusRequiredException("Status must be either " + validValues);
        }

        return CourseStatus.valueOf(value);
    }
}
