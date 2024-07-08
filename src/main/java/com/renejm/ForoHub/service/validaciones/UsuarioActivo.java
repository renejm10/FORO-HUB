package com.renejm.ForoHub.service.validaciones;

import com.renejm.ForoHub.domain.topico.TopicoRepository;
import com.renejm.ForoHub.domain.usuario.UsuarioRepository;
import com.renejm.ForoHub.infra.errores.ValidacionIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioActivo implements ValidadorDeTopico{

    @Autowired
    UsuarioRepository repository;
    public void validar(TopicoValidarDTO datos) {
        if (datos.usuario_id()!=null && !repository.existsById(datos.usuario_id())){
            throw new ValidacionIntegridad("Usuario no encontrado");
        }
    }
}
