package com.projexapi.projexapi.Auditoria.AlteracoesContratos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projexapi.projexapi.Contrato.Contrato;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoAlteracaoContrato;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "alteracoes_contratos_auditoria")
public class AlteracoesContratosAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alteracao_auditoria")
    private Long id;

    @Column(name = "id_alteracao")
    private Long id_alteracao;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo")
    private TipoAlteracaoContrato tipo;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo_operacao")
    private TipoOperacao tipo_operacao;
}
