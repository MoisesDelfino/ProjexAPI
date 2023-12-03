package com.projexapi.projexapi.Contrato;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface ContratoRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

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

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar {

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


        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;

        private LocalDateTime data_vigencia;

        private String razao_social;

        private Integer numero_contrato;

        private String objeto;

        private BigDecimal valor_inicial;

        private BigDecimal valor_final;

        private ProjetoRepresentation.Padrao projeto;

        private Ativo ativo;



        public static ContratoRepresentation.Detalhes from(Contrato contrato) {
            return Detalhes.builder()
                    .id(contrato.getId())
                    .data_vigencia(contrato.getData_vigencia())
                    .razao_social(contrato.getRazao_social())
                    .numero_contrato(contrato.getNumero_contrato())
                    .objeto(contrato.getObjeto())
                    .valor_inicial(contrato.getValor_inicial())
                    .valor_final(contrato.getValor_final())
                    .projeto(ProjetoRepresentation.Padrao.from(contrato.getProjeto()))
                    .ativo(contrato.getAtivo())
                    .build();
        }
        public static List<ContratoRepresentation.Detalhes> from(List<Contrato> ContratoList) {
            return ContratoList
                    .stream()
                    .map(ContratoRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;

        private LocalDateTime data_vigencia;

        private String razao_social;

        private Integer numero_contrato;

        private String objeto;

        private BigDecimal valor_inicial;

        private BigDecimal valor_final;

        private ProjetoRepresentation.Padrao projeto;

        private Ativo ativo;


        private static ContratoRepresentation.Lista from(Contrato contrato) {
            return Lista.builder()
                    .id(contrato.getId())
                    .data_vigencia(contrato.getData_vigencia())
                    .razao_social(contrato.getRazao_social())
                    .numero_contrato(contrato.getNumero_contrato())
                    .objeto(contrato.getObjeto())
                    .valor_inicial(contrato.getValor_inicial())
                    .valor_final(contrato.getValor_final())
                    .projeto(ProjetoRepresentation.Padrao.from(contrato.getProjeto()))
                    .ativo(contrato.getAtivo())
                    .build();
        }
        public static List<ContratoRepresentation.Lista> from(List<Contrato> ContratoList) {
            return ContratoList
                    .stream()
                    .map(ContratoRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
   }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Padrao {
        private Long id;

        private LocalDateTime data_vigencia;

        private String razao_social;

        private Integer numero_contrato;

        private String objeto;

        private BigDecimal valor_inicial;

        private BigDecimal valor_final;

        private ProjetoRepresentation.Padrao projeto;

        private Ativo ativo;

       public static Padrao from(Contrato contrato) {
            return Padrao.builder()
                    .id(contrato.getId())
                    .data_vigencia(contrato.getData_vigencia())
                    .razao_social(contrato.getRazao_social())
                    .numero_contrato(contrato.getNumero_contrato())
                    .objeto(contrato.getObjeto())
                    .valor_inicial(contrato.getValor_inicial())
                    .valor_final(contrato.getValor_final())
                    .projeto(ProjetoRepresentation.Padrao.from(contrato.getProjeto()))
                    .ativo(contrato.getAtivo())
                    .build();
        }
        public static List<Padrao> from(List<Contrato> contratoList) {
            return contratoList
                    .stream()
                    .map(Padrao::from)
                    .collect(Collectors.toList());
        }
    }
}
