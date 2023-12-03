package com.projexapi.projexapi.Auditoria.Contrato;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface ContratoAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O id_contrato nome não pode ser nulo")
        private long id_contrato;

        @NotNull(message = "O data_vigencia nome não pode ser nulo")
        private LocalDateTime data_vigencia;

        @NotNull(message = "O campo razao_social não pode ser nulo")
        private String razao_social;

        @NotNull(message = "O campo numero_contrato não pode ser nulo")
        private Integer numero_contrato;

        @NotNull(message = "O campo objeto não pode ser nulo")
        private String objeto;

        @NotNull(message = "O campo valor_inicial não pode ser nulo")
        private BigDecimal valor_inicial;

        @NotNull(message = "O campo valor_final não pode ser nulo")
        private BigDecimal valor_final;

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

        private Long id_contrato;

        private LocalDateTime data_vigencia;

        private String razao_social;

        private Integer numero_contrato;

        private String objeto;

        private BigDecimal valor_inicial;

        private BigDecimal valor_final;

        private Ativo ativo;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;


        public static ContratoAuditoriaRepresentation.Detalhes from(ContratoAuditoria contratoAuditoria) {
            return Detalhes.builder()
                    .id(contratoAuditoria.getId())
                    .id_contrato(contratoAuditoria.getId_contrato())
                    .data_vigencia(contratoAuditoria.getData_vigencia())
                    .razao_social(contratoAuditoria.getRazao_social())
                    .numero_contrato(contratoAuditoria.getNumero_contrato())
                    .objeto(contratoAuditoria.getObjeto())
                    .valor_inicial(contratoAuditoria.getValor_inicial())
                    .valor_final(contratoAuditoria.getValor_final())
                    .ativo(contratoAuditoria.getAtivo())
                    .usuario(contratoAuditoria.getUsuario())
                    .data_hora(contratoAuditoria.getData_hora())
                    .tipo_operacao(contratoAuditoria.getTipo_operacao())
                    .build();
        }

        public static List<ContratoAuditoriaRepresentation.Detalhes> from(List<ContratoAuditoria> contratoList) {
            return contratoList.stream()
                    .map(ContratoAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}
