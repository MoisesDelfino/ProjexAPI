package com.projexapi.projexapi.AlteracoesContratos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projexapi.projexapi.Contrato.Contrato;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoAlteracaoContrato;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "alteracoes_contratos")
public class AlteracoesContratos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alteracao")
    private Long id;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "tipo")
    private TipoAlteracaoContrato tipo;

    @ManyToOne
    @JsonIgnore
    private Contrato contrato;

    @Column(name = "ativo")
    private Ativo ativo;
}
