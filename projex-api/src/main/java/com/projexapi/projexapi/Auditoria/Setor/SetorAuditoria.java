package com.projexapi.projexapi.Auditoria.Setor;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "setor_auditoria")
public class SetorAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "id_setor")
    private Long id_setor;

    @Column(name = "nome")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "administrador")
    private Boolean administrador;

//    @Column(name = "gerente")
//    private Boolean gerente;

    @Column(name = "ativo")
    private Ativo ativo;

    @Column(name = "token_dispositivo")
    @ElementCollection
    @CollectionTable(name = "setor_tokens_dispositivo", joinColumns = @JoinColumn(name = "setor_id"))
    @Size(max = 5)
    private List<String> tokensDispositivo = new ArrayList<>();

    @Column(name="usuario")
    private String usuario;

    @Column(name="data_hora")
    private Timestamp data_hora;

    @Column(name="tipo")
    private TipoOperacao tipo_operacao;
}
