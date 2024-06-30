package com.renejm.ForoHub.controller;


import com.renejm.ForoHub.domain.topico.*;
import com.renejm.ForoHub.domain.usuario.Usuario;
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
    private TopicoDatosService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalleTopicoDTO> registrarTopico(@RequestBody @Valid TopicoRegistroDTO dtoTopico,
                                                              UriComponentsBuilder uriBuilder) {

        var response = service.registrar(dtoTopico);

        URI url = uriBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    };
}
