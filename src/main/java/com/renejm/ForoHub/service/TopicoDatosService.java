package com.renejm.ForoHub.service;


import com.renejm.ForoHub.controller.TopicoRespuestaDTO;
import com.renejm.ForoHub.domain.curso.CursoDTO;
import com.renejm.ForoHub.domain.curso.CursoRepository;
import com.renejm.ForoHub.domain.topico.*;
import com.renejm.ForoHub.domain.usuario.UsuarioDTO;
import com.renejm.ForoHub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoDatosService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;



    public TopicoDetalleDTO registrarTopico(TopicoRegistroDTO topicoRegistroDTO){

        var usuario = usuarioRepository.findById(topicoRegistroDTO.usuario()).get();
        var curso = cursoRepository.findById(topicoRegistroDTO.curso()).get();
        var topico = new Topico(topicoRegistroDTO,usuario,curso);

        topicoRepository.save(topico);
        return new TopicoDetalleDTO(topico);
    }

    public TopicoRespuestaDTO verTopico(Long id){
        Topico topico = topicoRepository.findById(id).orElse(null);
        TopicoRespuestaDTO datosTopico = null;
        if (topico != null) {
            datosTopico = new TopicoRespuestaDTO(
                    topico.getId(),
                    topico.getTitulo(),
                    topico.getMensaje(),
                    topico.getFechaCreacion(),
                    topico.getStatus(),
                    new UsuarioDTO(topico.getUsuario().getNombre()),
                    new CursoDTO(topico.getCurso().getNombre(), topico.getCurso().getCategoria().toString()));
        }
        return datosTopico;
    }



}
