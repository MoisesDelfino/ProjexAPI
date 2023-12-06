package com.projexapi.projexapi.Setor;

import com.projexapi.projexapi.Auth.Models.Role;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Usuario.Usuario;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(	name = "setor")
public class Setor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ativo")
    private Ativo ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "setor", orphanRemoval = false)
    List<Usuario> usuarioList = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "setor", orphanRemoval = false)
    List<Projeto> projetoList = new ArrayList<>();
}
