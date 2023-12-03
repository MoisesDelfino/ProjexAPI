package com.projexapi.projexapi.Auditoria.Contrato;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "contrato_auditoria")
public class ContratoAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_contrato")
    private Long id_contrato;

    @Column(name = "data_vigencia")
    private LocalDateTime data_vigencia;

    @Column(name = "razao_social")
    private String razao_social;

    @Column(name = "numero_contrato")
    private Integer numero_contrato;

    @Column(name = "objeto")
    private String objeto;

    @Column(name = "valor_inicial")
    private BigDecimal valor_inicial;

    @Column(name = "valor_final")
    private BigDecimal valor_final;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
