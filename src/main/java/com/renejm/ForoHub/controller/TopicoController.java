package com.renejm.ForoHub.controller;



import com.renejm.ForoHub.domain.topico.*;


import com.renejm.ForoHub.service.TopicoDatosService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<TopicoDetalleDTO> registrarTopico(@RequestBody @Valid TopicoRegistroDTO dtoTopico,
                                                            UriComponentsBuilder uriBuilder) {
        var response = service.registrarTopico(dtoTopico);
        URI url = uriBuilder.path("/topico/{id}").buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(url).body(response);
    };

    @GetMapping
    public ResponseEntity<Page<TopicoListadoDTO>> listarTopicos(@PageableDefault(size = 10,sort = "fechaCreacion",direction = Sort.Direction.ASC)  Pageable pageable) {
        return ResponseEntity.ok(topicoRepository.findAll(pageable).map(TopicoListadoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoRespuestaDTO> obtenerTopico(@PathVariable Long id) {
        var datosTopico = service.verTopico(id);
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity actualizarTopico(@PathVariable Long id, @RequestBody @Valid TopicoActualizarDTO topicoActualizarDTO){
        var actualizado = service.ActualizarTopico(id,topicoActualizarDTO);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        service.EliminarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
