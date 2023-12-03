package com.projexapi.projexapi.Etapa;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Fase;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Projeto.Projeto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "acao")
public class Etapa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etapa")
    private Long id;

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

    @ManyToOne
    private Projeto projeto;

    private Ativo ativo;

    private Fase fase;

}
