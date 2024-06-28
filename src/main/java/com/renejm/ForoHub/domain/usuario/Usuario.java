package com.renejm.ForoHub.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "usuario")
@Entity(name= "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String password;
}
