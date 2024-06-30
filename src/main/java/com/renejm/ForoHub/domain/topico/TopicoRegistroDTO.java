package com.renejm.ForoHub.domain.topico;
import jakarta.validation.constraints.NotNull;



public record TopicoRegistroDTO(
        @NotNull String titulo,
        @NotNull String mensaje,
        @NotNull Long autor,
        @NotNull Long curso) {
}
