package com.renejm.ForoHub.service;


import com.renejm.ForoHub.domain.topico.TopicoRespuestaDTO;
import com.renejm.ForoHub.domain.curso.Curso;
import com.renejm.ForoHub.domain.curso.CursoDTO;
import com.renejm.ForoHub.domain.curso.CursoRepository;
import com.renejm.ForoHub.domain.topico.*;
import com.renejm.ForoHub.service.validaciones.TopicoValidarDTO;
import com.renejm.ForoHub.service.validaciones.ValidadorDeTopico;
import com.renejm.ForoHub.domain.usuario.UsuarioDTO;
import com.renejm.ForoHub.domain.usuario.UsuarioRepository;
import com.renejm.ForoHub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoDatosService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private List<ValidadorDeTopico> validadores;
    
    private TopicoValidarDTO topicoValidarDTO;
    
    public TopicoDetalleDTO registrarTopico(TopicoRegistroDTO topicoRegistroDTO){
        
        topicoValidarDTO = new TopicoValidarDTO(
                topicoRegistroDTO.titulo(),
                topicoRegistroDTO.mensaje(),
                topicoRegistroDTO.usuario(),
                topicoRegistroDTO.curso());
        validadores.forEach(v -> v.validar(topicoValidarDTO));
        
        var usuario = usuarioRepository.findById((topicoRegistroDTO.usuario() != null) ? topicoRegistroDTO.usuario() : null).get();
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

    public TopicoDetalleDTO ActualizarTopico(Long id,TopicoActualizarDTO topicoActualizarDTO){

        if (!topicoRepository.existsById(id)) {
            throw new ValidacionIntegridad("El topico no existe");
        }else {
            topicoValidarDTO = new TopicoValidarDTO(
                    topicoActualizarDTO.titulo(),
                    topicoActualizarDTO.mensaje(),
                    topicoActualizarDTO.usuario_id(),
                    topicoActualizarDTO.curso_id());
            validadores.forEach(v -> v.validar(topicoValidarDTO));
        }

        Topico topico = topicoRepository.getReferenceById(id);

        if (topicoActualizarDTO.titulo() != null) {
            topico.setTitulo(topicoActualizarDTO.titulo());
        }
        if (topicoActualizarDTO.mensaje() != null) {
            topico.setMensaje(topicoActualizarDTO.mensaje());
        }
        if(topicoActualizarDTO.status() != null){
            topico.setStatus(topicoActualizarDTO.status());
        }
        if(topicoActualizarDTO.curso_id() != null){
            var responseCurso = cursoRepository.findById(topicoActualizarDTO.curso_id());
            if (responseCurso.isPresent()){
                Curso curso = new Curso(responseCurso);
                topico.setCurso(curso);
            }
        }

        return new TopicoDetalleDTO(topico);
    }


    public void EliminarTopico(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new ValidacionIntegridad("El topico no existe");
        }else{
            topicoRepository.deleteById(id);
        }

    }

}
