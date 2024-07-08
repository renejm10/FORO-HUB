package com.renejm.ForoHub.infra.errores;

public class ValidacionIntegridad extends RuntimeException {
    public ValidacionIntegridad(String mensaje) {
        super(mensaje);
    }
}
