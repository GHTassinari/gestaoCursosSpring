package br.com.guilhermetassinari.gestao_cursos.modules.courses.entities;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.deserializers.CourseStatusDeserializer;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
public class CourseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Course name is obligatory")
    @Size(min = 1, max = 60, message = "Course name must have between 1 and 60 characters")
    private String name;

    @NotBlank(message = "Category is obligatory")
    @Size(min = 1, max = 30, message = "Category name must have between 1 and 30 characters")
    private String category;

    @NotNull(message = "Status is obligatory")
    @Enumerated(EnumType.STRING)
    @JsonDeserialize(using = CourseStatusDeserializer.class)
    private CourseStatus status;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

