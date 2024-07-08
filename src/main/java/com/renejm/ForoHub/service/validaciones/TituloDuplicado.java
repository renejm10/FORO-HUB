package com.renejm.ForoHub.service.validaciones;

import com.renejm.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TituloDuplicado implements ValidadorDeTopico {

    @Autowired
    TopicoRepository repository;

    public void validar(TopicoValidarDTO datos) {

        if (repository.buscarDuplicadoTitulo(datos.titulo()).isPresent()) {
            throw new ValidationException("No se permiten titulos duplicados");
        }
    }
}
