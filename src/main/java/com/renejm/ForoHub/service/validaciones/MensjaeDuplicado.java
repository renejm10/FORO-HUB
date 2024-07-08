package com.renejm.ForoHub.service.validaciones;

import com.renejm.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MensjaeDuplicado implements ValidadorDeTopico {

    @Autowired
    TopicoRepository repository;

    public void validar(TopicoValidarDTO datos) {


        if (repository.buscarDuplicadoMensaje(datos.mensaje()).isPresent()) {
            throw new ValidationException("No se permiten mensajes duplicados");
        }
    }
}
