package com.projexapi.projexapi.Auditoria.Risco;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.NivelRisco;
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
@Table(name = "risco_auditoria")
public class RiscoAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_risco")
    private Long id_risco;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nivel")
    private NivelRisco nivel;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
