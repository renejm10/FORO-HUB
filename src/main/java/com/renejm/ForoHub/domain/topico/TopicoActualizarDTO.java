package com.renejm.ForoHub.domain.topico;


public record TopicoActualizarDTO(
        String titulo,
        String mensaje,
        Boolean status,
        Long usuario_id,
        Long curso_id
) {
}
