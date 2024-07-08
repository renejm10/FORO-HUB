package com.renejm.ForoHub.domain.topico;

import com.renejm.ForoHub.domain.curso.CursoDTO;
import com.renejm.ForoHub.domain.usuario.UsuarioDTO;

import java.time.LocalDateTime;

public record TopicoRespuestaDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        UsuarioDTO usuario,
        CursoDTO curso
) {
}
