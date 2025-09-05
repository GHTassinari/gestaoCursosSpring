package br.com.guilhermetassinari.gestao_cursos.modules.cursos.repositories;

import br.com.guilhermetassinari.gestao_cursos.modules.cursos.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
    Optional<CourseEntity> findByName(String name);
}
