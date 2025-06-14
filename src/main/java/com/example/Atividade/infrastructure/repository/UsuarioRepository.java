package com.example.Atividade.infrastructure.repository;

import com.example.Atividade.infrastructure.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByEmail(String email);

    @Query("""
            SELECT u
            FROM Usuario u
            WHERE role = 'USER'
            """)
    List<UsuarioEntity> findAllByRoleUser();
}
