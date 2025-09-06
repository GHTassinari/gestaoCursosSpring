package br.com.guilhermetassinari.gestao_cursos.modules.courses.controllers;

import br.com.guilhermetassinari.gestao_cursos.exceptions.InvalidCourseStatusException;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.dto.*;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases.CreateCourseUseCase;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases.FullUpdateCourseUseCase;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases.GetCourseUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CreateCourseUseCase createCourseUseCase;

    private final GetCourseUseCase getCourseUseCase;

    private final FullUpdateCourseUseCase fullUpdateCourseUseCase;

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

    @GetMapping
    public ResponseEntity<GetCoursePageDTO<GetCourseResponseDTO>> getCourses(
            @RequestParam Map<String, String> allParams,
            @ModelAttribute GetCourseRequestDTO requestDTO
    ) {
        Set<String> dtoFields = Arrays.stream(GetCourseRequestDTO.class.getDeclaredFields())
                .map(Field::getName)
                .collect(Collectors.toSet());

        for (String param : allParams.keySet()) {
            if (!dtoFields.contains(param)) {
                return ResponseEntity.badRequest().build();
            }
        }

        UUID id = null;
        if (requestDTO.getId() != null && !requestDTO.getId().isBlank()) {
            try {
                id = UUID.fromString(requestDTO.getId());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().build();
            }
        }

        CourseStatus status = null;
        if (requestDTO.getStatus() != null && !requestDTO.getStatus().isBlank()) {
            try {
                status = CourseStatus.valueOf(requestDTO.getStatus().toUpperCase());
            } catch (IllegalArgumentException e) {
                String validValues = Arrays.stream(CourseStatus.values())
                        .map(Enum::name)
                        .collect(Collectors.joining(" or "));
                throw new InvalidCourseStatusException("Status must be either " + validValues);
            }
        }

        CourseEntity filter = CourseEntity.builder()
                .id(id)
                .name(requestDTO.getName())
                .category(requestDTO.getCategory())
                .status(status)
                .build();

        Page<CourseEntity> page = getCourseUseCase.execute(filter, requestDTO.getPage(), requestDTO.getSize());

        List<GetCourseResponseDTO> content = page.getContent().stream()
                .map(c -> new GetCourseResponseDTO(c.getName(), c.getCategory(), c.getStatus()))
                .toList();

        GetCoursePageDTO<GetCourseResponseDTO> response = new GetCoursePageDTO<>(
                content,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> fullUpdate(@PathVariable String id,
                                           @Valid @RequestBody FullUpdateCourseDTO fullUpdateCourseDTO){
        UUID courseId = UUID.fromString(id);

        var courseEntity = CourseEntity.builder()
                .name(fullUpdateCourseDTO.getName())
                .category(fullUpdateCourseDTO.getCategory())
                .status(fullUpdateCourseDTO.getStatus())
                .build();

        this.fullUpdateCourseUseCase.execute(courseId, courseEntity);

        return ResponseEntity.ok().build();
    }

}
