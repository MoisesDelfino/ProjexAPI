package com.projexapi.projexapi.Projeto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projexapi.projexapi.Etapa.Etapa;
import com.projexapi.projexapi.Comentario.Comentario;
import com.projexapi.projexapi.Contrato.Contrato;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Responsavel.Responsavel;
import com.projexapi.projexapi.Risco.Risco;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Usuario.Usuario;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "projeto")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto")
    private Long id;

    @Column(name = "sequencial")
    private Long sequencial;

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

    @Column(name = "data_previsao_inicio")
    private LocalDateTime data_previsao_inicio;

    @Column(name = "data_inicio")
    private LocalDateTime data_inicio;

    @Column(name = "data_previsao_fim")
    private LocalDateTime data_previsao_fim;

    @Column(name = "data_fim")
    private LocalDateTime data_fim;

    @Column(name = "orcamento_inicial")
    private BigDecimal orcamento_inicial;

    @Column(name = "custo_previsto")
    private BigDecimal custo_previsto;

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projeto", orphanRemoval = false)
    @JsonIgnore
    private List<Etapa> etapaList = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Responsavel responsavel;

    @ManyToOne
    @JsonIgnore
    private Setor setor;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "riscos_projetos",
               joinColumns = @JoinColumn(name = "projeto_fk"),
               inverseJoinColumns = @JoinColumn(name = "risco_fk"))
    private List<Risco> riscoList;

    @Column(name = "ativo")
    private Ativo ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projeto", orphanRemoval = false)
    @JsonIgnore
    List<Contrato> contratoList = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "projeto", orphanRemoval = false)
    @JsonIgnore
    List<Comentario> comentarioList = new ArrayList<>();

    @Column(name = "dias_prazo_finalizacao")
    private int dias_prazo_finalizacao;

    @Column(name = "arquivos")
    @ElementCollection
    private List<String> arquivos = new ArrayList<>();

    @Column(name = "percentual_conclusao")
    private BigDecimal percentual_conclusao;
}
