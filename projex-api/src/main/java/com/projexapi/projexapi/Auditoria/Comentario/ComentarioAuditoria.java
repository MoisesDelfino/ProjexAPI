package com.projexapi.projexapi.Auditoria.Comentario;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "comentario_auditoria")
public class ComentarioAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_comentario")
    private Long id_comentario;

    @Column(name = "id_comentario_pai")
    private Long id_comentario_pai;

    @Column(name = "conteudo")
    private String conteudo;

    @Column(name = "data_inclusao")
    private LocalDateTime data_inclusao;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
