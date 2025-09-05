package br.com.guilhermetassinari.gestao_cursos.modules.cursos.useCases;

import br.com.guilhermetassinari.gestao_cursos.exceptions.NameFoundException;
import br.com.guilhermetassinari.gestao_cursos.modules.cursos.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.cursos.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCourseUseCase {
    private final CourseRepository courseRepository;

    public CourseEntity execute(CourseEntity courseEntity) {
        this.courseRepository.findByName(courseEntity.getName()).ifPresent(course -> {
            throw new NameFoundException();
        });

        return courseRepository.save(courseEntity);
    }
}
