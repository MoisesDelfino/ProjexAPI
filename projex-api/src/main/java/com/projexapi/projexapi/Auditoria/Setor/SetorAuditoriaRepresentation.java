package com.projexapi.projexapi.Auditoria.Setor;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public interface SetorAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O campo id_setor não pode ser nulo")
        private long id_setor;
        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String username;

        private String email;

        private String password;

        private Boolean administrador;

        //private Boolean gerente;

        private Ativo ativo;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Detalhes {
        private Long id;

        private Long id_setor;

        private String username;

        private String email;

        private String password;

        private Boolean administrador;

        //private Boolean gerente;

        private Ativo ativo;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;


        public static SetorAuditoriaRepresentation.Detalhes from(SetorAuditoria setorAuditoria) {
            return Detalhes.builder()
                    .id(setorAuditoria.getId())
                    .id_setor(setorAuditoria.getId_setor())
                    .username(setorAuditoria.getUsername())
                    .email(setorAuditoria.getEmail())
                    .password(setorAuditoria.getPassword())
                    .administrador(setorAuditoria.getAdministrador())
                    //.gerente(setorAuditoria.getGerente())
                    .ativo(setorAuditoria.getAtivo())
                    .usuario(setorAuditoria.getUsuario())
                    .data_hora(setorAuditoria.getData_hora())
                    .tipo_operacao(setorAuditoria.getTipo_operacao())
                    .build();
        }
        public static List<SetorAuditoriaRepresentation.Detalhes> from(List<SetorAuditoria> setorList) {
            return setorList
                    .stream()
                    .map(SetorAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }

    }
}
