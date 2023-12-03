package com.projexapi.projexapi.Auditoria.Acao;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Fase;
import com.projexapi.projexapi.Enums.Status;
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
@Table(name = "acao_auditoria")
public class AcaoAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_acao")
    private Long id_acao;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_previsao_inicio")
    private LocalDateTime data_previsao_inicio;

    @Column(name = "data_inicio")
    private LocalDateTime data_inicio;

    @Column(name = "data_previsao_fim")
    private LocalDateTime data_previsao_fim;

    @Column(name = "data_fim")
    private LocalDateTime data_fim;

    @Column(name = "custo")
    private BigDecimal custo;

    @Column(name = "status")
    private Status status;

    private Ativo ativo;

    private Fase fase;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
