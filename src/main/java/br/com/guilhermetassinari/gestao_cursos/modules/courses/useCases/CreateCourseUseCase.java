package br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases;

import br.com.guilhermetassinari.gestao_cursos.exceptions.NameFoundException;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCourseUseCase {
    private final CourseRepository courseRepository;

    public void execute(CourseEntity courseEntity) {
        this.courseRepository.findByName(courseEntity.getName()).ifPresent(course -> {
            throw new NameFoundException();
        });

        courseRepository.save(courseEntity);
    }
}
