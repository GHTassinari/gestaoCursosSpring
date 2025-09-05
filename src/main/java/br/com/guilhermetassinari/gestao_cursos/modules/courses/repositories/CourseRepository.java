package br.com.guilhermetassinari.gestao_cursos.modules.courses.repositories;

import br.com.guilhermetassinari.gestao_cursos.modules.courses.entities.CourseEntity;
import br.com.guilhermetassinari.gestao_cursos.modules.courses.enums.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID>, JpaSpecificationExecutor<CourseEntity> {
    Optional<CourseEntity> findByName(String name);
    Optional<CourseEntity> findByCategory(String category);
    Optional<CourseEntity> findByStatus(CourseStatus status);
}
