package com.projexapi.projexapi.Contrato;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projexapi.projexapi.AlteracoesContratos.AlteracoesContratos;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.Projeto;
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
@Table(name = "contrato")
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contrato")
    private Long id;

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

    @ManyToOne
    @JsonIgnore
    private Projeto projeto;

    @Column(name = "ativo")
    private Ativo ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contrato", orphanRemoval = false)
    @JsonIgnore
    List<AlteracoesContratos> alteracoesContratosList = new ArrayList<>();

}
