package com.renejm.ForoHub.service.validaciones;

import com.renejm.ForoHub.domain.curso.CursoRepository;
import com.renejm.ForoHub.domain.usuario.UsuarioRepository;
import com.renejm.ForoHub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoActivo implements ValidadorDeTopico{

    @Autowired
    CursoRepository repository;

    public void validar(TopicoValidarDTO datos) {
        if(datos.curso_id()!=null && repository.findById(datos.curso_id()).isEmpty()){
            throw new ValidacionIntegridad("Curso no encontrado");
        }
    }
}
