package com.renejm.ForoHub.domain.topico;


import com.renejm.ForoHub.domain.curso.Curso;
import com.renejm.ForoHub.domain.respuestas.Respuesta;
import com.renejm.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topico")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @OneToMany(mappedBy = "topico_id", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    public Topico (TopicoRegistroDTO topicoRegistroDTO, Usuario usuario, Curso curso) {
        this.titulo=topicoRegistroDTO.titulo();
        this.mensaje=topicoRegistroDTO.mensaje();
        this.fechaCreacion =LocalDateTime.now();
        this.status=true;
        this.usuario= usuario;
        this.curso=curso;
    }


    public void DesactivarTopico(){
        this.status=false;
    }

}
