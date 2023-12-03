package com.projexapi.projexapi.Auditoria.Acao;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Fase;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface AcaoAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O campo id_acao não pode ser nulo")
        private Long id_acao;
        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;
        @NotNull(message = "O campo descricao não pode ser nulo")
        private String descricao;
        @NotNull(message = "O campo data_previsao_inicio não pode ser nulo")
        private LocalDateTime data_previsao_inicio;

        private LocalDateTime data_inicio;
        @NotNull(message = "O campo data_previsao_fim não pode ser nulo")
        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;
        @NotNull(message = "O campo custo não pode ser nulo")
        private BigDecimal custo;
        @NotNull(message = "O campo status não pode ser nulo")
        private Status status;

        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

        @NotNull(message = "O campo fase não pode ser nulo")
        private Fase fase;

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

        private Long id_acao;

        private String nome;

        private String descricao;

        private LocalDateTime data_previsao_inicio;

        private LocalDateTime data_inicio;

        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;

        private BigDecimal custo;

        private Status status;

        private Ativo ativo;

        private Fase fase;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;


        public static AcaoAuditoriaRepresentation.Detalhes from(AcaoAuditoria acaoAuditoria) {
            return Detalhes.builder()
                    .id(acaoAuditoria.getId())
                    .id_acao(acaoAuditoria.getId_acao())
                    .nome(acaoAuditoria.getNome())
                    .descricao(acaoAuditoria.getDescricao())
                    .data_previsao_inicio(acaoAuditoria.getData_previsao_inicio())
                    .data_inicio(acaoAuditoria.getData_inicio())
                    .data_previsao_fim(acaoAuditoria.getData_previsao_fim())
                    .data_fim(acaoAuditoria.getData_fim())
                    .custo(acaoAuditoria.getCusto())
                    .status(acaoAuditoria.getStatus())
                    .ativo(acaoAuditoria.getAtivo())
                    .usuario(acaoAuditoria.getUsuario())
                    .data_hora(acaoAuditoria.getData_hora())
                    .tipo_operacao(acaoAuditoria.getTipo_operacao())
                    .build();
        }
        public static List<AcaoAuditoriaRepresentation.Detalhes> from(List<AcaoAuditoria> acaoList) {
            return acaoList.stream()
                    .map(AcaoAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}
