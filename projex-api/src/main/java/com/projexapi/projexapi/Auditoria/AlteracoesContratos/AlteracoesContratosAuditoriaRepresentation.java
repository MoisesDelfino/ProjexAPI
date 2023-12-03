package com.projexapi.projexapi.Auditoria.AlteracoesContratos;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoAlteracaoContrato;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public interface AlteracoesContratosAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar{

        @NotNull(message = "O campo id_alteracao_contrato não pode ser nulo")
        private Long id_alteracao_contrato;

        @NotNull(message = "O valor nome não pode ser nulo")
        private BigDecimal valor;

        @NotNull(message = "O campo tipo não pode ser nulo")
        private TipoAlteracaoContrato tipo;

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

        private Long id_alteracao_contrato;

        private BigDecimal valor;

        private TipoAlteracaoContrato tipo;

        private Ativo ativo;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;


        public static AlteracoesContratosAuditoriaRepresentation.Detalhes from(AlteracoesContratosAuditoria alteracaoContratosAuditoria) {
            return Detalhes.builder()
                    .id(alteracaoContratosAuditoria.getId())
                    .id_alteracao_contrato(alteracaoContratosAuditoria.getId_alteracao())
                    .valor(alteracaoContratosAuditoria.getValor())
                    .tipo(alteracaoContratosAuditoria.getTipo())
                    .ativo(alteracaoContratosAuditoria.getAtivo())
                    .usuario(alteracaoContratosAuditoria.getUsuario())
                    .data_hora(alteracaoContratosAuditoria.getData_hora())
                    .tipo_operacao(alteracaoContratosAuditoria.getTipo_operacao())
                    .build();
        }
        public static List<AlteracoesContratosAuditoriaRepresentation.Detalhes> from(List<AlteracoesContratosAuditoria> alteracaoContratosAuditoriaList) {
            return alteracaoContratosAuditoriaList
                    .stream()
                    .map(AlteracoesContratosAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}
