package br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteCourseUseCase {
    private final CourseRepository courseRepository;

    public void execute(UUID id){
        CourseEntity existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with id: " + id));

        courseRepository.delete(existingCourse);
    }
}
