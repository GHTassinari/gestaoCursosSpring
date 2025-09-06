package br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases;

import br.com.guilhermetassinari.gestao_cursos.exceptions.NameFoundException;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.repositories.CourseRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FullUpdateCourseUseCase {
    private final CourseRepository courseRepository;

    public void execute(UUID id, CourseEntity courseEntity){
        CourseEntity existingCourse = this.courseRepository.findById(id)
                .orElseThrow(() -> {
                    System.out.println("Throwing EntityNotFoundException for id: " + id);
                    return new EntityNotFoundException("Course not found with id: " + id);
                });

        // Verifies if the name already exists, and it isn't the name of the course currently being used
        this.courseRepository.findByName(courseEntity.getName()).ifPresent(course -> {
            if(!course.getId().equals(id)){
                throw new NameFoundException();
            }
        });

        existingCourse.setName(courseEntity.getName());
        existingCourse.setCategory(courseEntity.getCategory());
        existingCourse.setStatus(courseEntity.getStatus());

        courseRepository.save(existingCourse);
    }
}
