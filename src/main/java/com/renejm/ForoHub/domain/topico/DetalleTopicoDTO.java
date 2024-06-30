package com.renejm.ForoHub.domain.topico;


import java.time.LocalDateTime;

public record DetalleTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        Long usuario_id,
        Long curso_id) {
    public DetalleTopicoDTO (Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_Creacion(),
                topico.getStatus(),
                topico.getAutor().getId(),
                topico.getCurso().getId());
    }
}
