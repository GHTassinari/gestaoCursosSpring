package br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UpdateStatusUseCase {
    private final CourseRepository courseRepository;

    public void execute(UUID id, CourseEntity courseEntity) {
        CourseEntity existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> {
                    System.out.println("Throwing EntityNotFoundException for id: " + id);
                    return new EntityNotFoundException("Course not found with id: " + id);
                });

        existingCourse.setStatus(courseEntity.getStatus());

        this.courseRepository.save(existingCourse);
    }
}
