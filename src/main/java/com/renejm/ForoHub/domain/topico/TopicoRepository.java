package com.renejm.ForoHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAll(Pageable pageable);

    @Query("select t from Topico t where t.titulo=:titulo")
    Optional<Topico> buscarDuplicadoTitulo(String titulo);

    @Query("select t from Topico t where t.mensaje=:mensaje")
    Optional<Topico> buscarDuplicadoMensaje(String mensaje);
}
