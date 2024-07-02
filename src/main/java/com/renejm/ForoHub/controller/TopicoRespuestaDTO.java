package com.renejm.ForoHub.controller;

import com.renejm.ForoHub.domain.curso.Curso;
import com.renejm.ForoHub.domain.curso.CursoDTO;
import com.renejm.ForoHub.domain.usuario.Usuario;
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
