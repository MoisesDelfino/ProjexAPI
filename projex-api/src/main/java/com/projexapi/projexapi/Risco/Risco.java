package com.projexapi.projexapi.Risco;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.NivelRisco;
import com.projexapi.projexapi.Projeto.Projeto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "risco")
public class Risco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_risco")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "nivel")
    private NivelRisco nivel;

    @Column(name = "ativo")
    private Ativo ativo;

}
