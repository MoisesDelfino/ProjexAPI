package com.projexapi.projexapi.Auditoria.Risco;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.NivelRisco;
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

public interface RiscoAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {
        @NotNull(message = "O campo nome não pode ser nulo")
        private Long id_risco;

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        private String descricao;

        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

        private NivelRisco nivel;

        @NotNull(message = "O campo usuario não pode ser nulo")
        private String usuario;

        @NotNull(message = "O campo data_hora não pode ser nulo")
        private Timestamp data_hora;

        @NotNull(message = "O campo tipo_operacao não pode ser nulo")
        private TipoOperacao tipo_operacao;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;

        private Long id_risco;

        private String nome;

        private String descricao;

        private NivelRisco nivel;

        private Ativo ativo;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;

        public static RiscoAuditoriaRepresentation.Detalhes from(RiscoAuditoria riscoAuditoria) {
            return Detalhes.builder()
                    .id(riscoAuditoria.getId())
                    .id_risco(riscoAuditoria.getId_risco())
                    .nome(riscoAuditoria.getNome())
                    .descricao(riscoAuditoria.getDescricao())
                    .nivel(riscoAuditoria.getNivel())
                    .ativo(riscoAuditoria.getAtivo())
                    .usuario(riscoAuditoria.getUsuario())
                    .data_hora(riscoAuditoria.getData_hora())
                    .tipo_operacao(riscoAuditoria.getTipo_operacao())
                    .build();
        }
        public static List<RiscoAuditoriaRepresentation.Detalhes> from(List<RiscoAuditoria> riscoList) {
            return riscoList.stream()
                    .map(RiscoAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}
