package com.projexapi.projexapi.Auditoria.Responsavel;

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

public interface ResponsavelAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O campo id_responsavel não pode ser nulo")
        private Long id_responsavel;

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        private String telefone;

        private String email;

        @NotNull(message = "O campo usuario não pode ser nulo")
        private String usuario;

        @NotNull(message = "O campo data_hora não pode ser nulo")
        private Timestamp data_hora;

        @NotNull(message = "O campo tipo_operacao não pode ser nulo")
        private TipoOperacao tipo_operacao;


        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;

        private Long id_responsavel;

        private String nome;

        private String telefone;

        private String email;

        private Ativo ativo;

        @NotNull(message = "O campo usuario não pode ser nulo")
        private String usuario;

        @NotNull(message = "O campo data_hora não pode ser nulo")
        private Timestamp data_hora;

        @NotNull(message = "O campo tipo_operacao não pode ser nulo")
        private TipoOperacao tipo_operacao;

        public static ResponsavelAuditoriaRepresentation.Detalhes from(ResponsavelAuditoria responsavelAuditoria) {
            return Detalhes.builder()
                    .id(responsavelAuditoria.getId())
                    .id_responsavel(responsavelAuditoria.getId_responsavel())
                    .nome(responsavelAuditoria.getNome())
                    .telefone(responsavelAuditoria.getTelefone())
                    .email(responsavelAuditoria.getEmail())
                    .ativo(responsavelAuditoria.getAtivo())
                    .usuario(responsavelAuditoria.getUsuario())
                    .data_hora(responsavelAuditoria.getData_hora())
                    .tipo_operacao(responsavelAuditoria.getTipo_operacao())
                    .build();
        }
        public static List<ResponsavelAuditoriaRepresentation.Detalhes> from(List<ResponsavelAuditoria> responsavelList) {
            return responsavelList.stream()
                    .map(ResponsavelAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}
