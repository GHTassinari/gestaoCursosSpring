package br.com.guilhermetassinari.gestao_cursos.modules.courses.useCases;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCourseUseCase {

    private final CourseRepository repository;

    public Page<CourseEntity> execute(CourseEntity courseEntity, int page, int size) {
        // Specification for dynamic filters
        Specification<CourseEntity> spec = (root, query, cb) -> cb.conjunction();

        if (courseEntity.getId() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("id"), courseEntity.getId()));
        }
        if (courseEntity.getName() != null && !courseEntity.getName().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + courseEntity.getName().toLowerCase() + "%"));
        }
        if (courseEntity.getCategory() != null && !courseEntity.getCategory().isBlank()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("category")), "%" + courseEntity.getCategory().toLowerCase() + "%"));
        }
        if (courseEntity.getStatus() != null) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("status"), courseEntity.getStatus()));
        }

        Pageable pageable = PageRequest.of(page, size); // Defines a page and its size
        return repository.findAll(spec, pageable);
    }
}
