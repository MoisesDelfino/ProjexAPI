package com.projexapi.projexapi.Comentario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Setor.Setor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comentario")
    private Long id;

    @Column(name = "id_comentario_pai")
    private Long id_comentario_pai;

    @ManyToOne
    @JsonIgnore
    private Setor setor;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "data_inclusao")
    private LocalDateTime data_inclusao;

    @ManyToOne
    @JsonIgnore
    private Projeto projeto;

    @Column(name = "ativo")
    private Ativo ativo;
}
