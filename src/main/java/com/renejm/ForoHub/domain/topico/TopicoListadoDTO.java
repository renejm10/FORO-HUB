package com.renejm.ForoHub.domain.topico;

import com.renejm.ForoHub.domain.curso.Curso;
import com.renejm.ForoHub.domain.usuario.Usuario;


import java.time.LocalDateTime;

public record TopicoListadoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String usuario,
        String curso
) {
    public TopicoListadoDTO(Topico topico) {
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getUsuario().toString(),
                topico.getCurso().toString());
    }
}
