package com.projexapi.projexapi.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projexapi.projexapi.Auth.Models.Role;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.Projeto;
import com.projexapi.projexapi.Setor.Setor;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(	name = "usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;

    @Size(max = 120)
    @Column(name = "password")
    private String password;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name = "token_dispositivo")
    @ElementCollection
    @CollectionTable(name = "setor_tokens_dispositivo", joinColumns = @JoinColumn(name = "setor_id"))
    @Size(max = 5)
    private List<String> tokensDispositivo = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", orphanRemoval = false)
    List<Projeto> projetoList = new ArrayList<>();

    @Column(name = "imagem")
    private String imagem;

    @ManyToOne
    @JsonIgnore
    private Setor setor;

    public Usuario(String username, String email, String encode, Setor setor) {
        this.username = username;
        this.email = email;
        this.password = encode;
        this.setor = setor;

        if (setor != null) {
            setor.getUsuarioList().add(this);
        }
    }
}
