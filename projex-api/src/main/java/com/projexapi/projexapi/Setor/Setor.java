package com.projexapi.projexapi.Setor;

import com.projexapi.projexapi.Auth.Models.Role;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.Projeto;
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
@Table(	name = "setor",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class Setor {
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


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "setor", orphanRemoval = false)
    List<Projeto> projetoList = new ArrayList<>();

    @Column(name = "imagem")
    private String imagem;

    public Setor(String username, String email, String encode) {
        this.username = username;
        this.email = email;
        this.password = encode;
    }
}
