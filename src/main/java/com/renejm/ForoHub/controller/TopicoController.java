package com.renejm.ForoHub.controller;


import com.renejm.ForoHub.domain.topico.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    @Autowired
    private TopicoDatosService service;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DetalleTopicoDTO> registrarTopico(@RequestBody @Valid TopicoRegistroDTO dtoTopico,
                                                              UriComponentsBuilder uriBuilder) {
        var response = service.registrar(dtoTopico);
        URI url = uriBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    };

    @GetMapping
    public ResponseEntity<Page<TopicoListadoDTO>> listarTopicos(@PageableDefault(size = 5,sort = "titulo")  Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findByStatusTrue(pageable).map(TopicoListadoDTO::new));
    }
}
