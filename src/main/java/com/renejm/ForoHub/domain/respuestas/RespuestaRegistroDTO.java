package com.renejm.ForoHub.domain.respuestas;

import com.renejm.ForoHub.domain.curso.Curso;
import com.renejm.ForoHub.domain.topico.Topico;
import com.renejm.ForoHub.domain.usuario.Usuario;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record RespuestaRegistroDTO(
        @NotNull String mensaje,
        @NotNull Topico topico,
        @NotNull @Future LocalDateTime fechaCreacion,
        @NotNull Usuario autor,
        @NotNull Curso curso
) {
}
