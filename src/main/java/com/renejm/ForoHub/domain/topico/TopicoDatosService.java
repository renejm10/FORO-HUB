package com.renejm.ForoHub.domain.topico;


import com.renejm.ForoHub.domain.curso.CursoRepository;
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



    public DetalleTopicoDTO registrar(TopicoRegistroDTO topicoRegistroDTO){

        var usuario = usuarioRepository.findById(topicoRegistroDTO.usuario()).get();
        var curso = cursoRepository.findById(topicoRegistroDTO.curso()).get();
        var topicoDTO =new TopicoDTO(topicoRegistroDTO.titulo(),topicoRegistroDTO.mensaje());
        var topico = new Topico(topicoDTO,usuario,curso);

        topicoRepository.save(topico);
        return new DetalleTopicoDTO(topico);
    }



}
