package com.renejm.ForoHub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record TopicoActualizarDTO(
        String titulo,
        String mensaje,
        Boolean status,
        Long curso_id
) {
}
