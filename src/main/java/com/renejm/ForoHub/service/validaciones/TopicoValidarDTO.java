package com.renejm.ForoHub.service.validaciones;

public record TopicoValidarDTO(
        String titulo,
        String mensaje,
        Long usuario_id,
        Long curso_id
) {
}
