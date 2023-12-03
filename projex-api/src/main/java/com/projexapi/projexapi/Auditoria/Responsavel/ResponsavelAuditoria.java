package com.projexapi.projexapi.Auditoria.Responsavel;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "responsavel_auditoria")
public class ResponsavelAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_responsavel")
    private Long id_responsavel;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "email")
    private String email;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
