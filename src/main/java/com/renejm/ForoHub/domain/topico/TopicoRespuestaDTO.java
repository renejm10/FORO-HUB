package com.renejm.ForoHub.domain.topico;

import com.renejm.ForoHub.domain.curso.Curso;
import com.renejm.ForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TopicoRespuestaDTO(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Usuario autor,
        Curso curso
) {
}
