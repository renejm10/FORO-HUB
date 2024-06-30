package com.renejm.ForoHub.controller;


import com.renejm.ForoHub.domain.topico.Topico;
import com.renejm.ForoHub.domain.topico.TopicoRegistroDTO;
import com.renejm.ForoHub.domain.topico.TopicoRepository;
import com.renejm.ForoHub.domain.topico.TopicoRespuestaDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoRespuestaDTO> registrarTopico(@RequestBody @Valid TopicoRegistroDTO dtoTopico,
                                                              UriComponentsBuilder uriBuilder) {
        Topico topico = repository.save(new Topico(dtoTopico));
        TopicoRespuestaDTO topicoRespuestaDTO = new TopicoRespuestaDTO(
                topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),topico.getAutor(),topico.getCurso());

        URI url = uriBuilder.path("/topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(topicoRespuestaDTO);
    };
}
