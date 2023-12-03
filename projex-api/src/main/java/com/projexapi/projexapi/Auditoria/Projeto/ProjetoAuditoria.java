package com.projexapi.projexapi.Auditoria.Projeto;

import com.projexapi.projexapi.Enums.Ativo;
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
@Table(name = "projeto_auditoria")
public class ProjetoAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto_auditoria")
    private Long id;

    @Column(name = "id_projeto")
    private Long id_projeto;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "justificativa")
    private String justificativa;

    @Column(name = "objetivo")
    private String objetivo;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "orcamento_inicial")
    private BigDecimal orcamento_inicial;

    @Column(name = "custo_real")
    private BigDecimal custo_real;

    @Column(name = "status")
    private Status status;

    @Column(name = "stakeholders")
    private String stakeholders;

    @Column(name = "beneficios")
    private String beneficios;

    @Column(name = "premissa")
    private String premissa;

    @Column(name = "restricao")
    private String restricao;

    @Column(name="responsavel")
    private String responsavel;

    @Column(name="setor")
    private String setor;

    @Column(name="ativo")
    private Ativo ativo;

    @Column(name = "percential_conclusao")
    private Double percentual_conslusao;

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
