package com.projexapi.projexapi.Etapa;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Fase;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public interface EtapaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

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

        @NotNull(message = "O campo fase não pode ser nulo")
        private Fase fase;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar {

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;
        @NotNull(message = "O campo descricao não pode ser nulo")
        private String descricao;

        @NotNull(message = "O campo data_previsao_inicio não pode ser nulo")
        private LocalDateTime data_previsao_inicio;
        @NotNull(message = "O campo data_inicio não pode ser nulo")
        private LocalDateTime data_inicio;
        @NotNull(message = "O campo data_previsao_fim não pode ser nulo")
        private LocalDateTime data_previsao_fim;
        @NotNull(message = "O campo data_fim não pode ser nulo")
        private LocalDateTime data_fim;
        ;
        @NotNull(message = "O campo custo não pode ser nulo")
        private BigDecimal custo;
        @NotNull(message = "O campo status não pode ser nulo")
        private Status status;

        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

        @NotNull(message = "O campo fase não pode ser nulo")
        private Fase fase;

    }
    @Data
    @Builder
    class Detalhes {
        private Long id;

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

        private ProjetoRepresentation.Padrao projeto;


        public static Detalhes from(Etapa etapa) {
            return Detalhes.builder()
                    .id(etapa.getId())
                    .nome(etapa.getNome())
                    .descricao(etapa.getDescricao())
                    .data_previsao_inicio(etapa.getData_previsao_inicio())
                    .data_inicio(etapa.getData_inicio())
                    .data_previsao_fim(etapa.getData_previsao_fim())
                    .data_fim(etapa.getData_fim())
                    .custo(etapa.getCusto())
                    .status(etapa.getStatus())
                    .ativo(etapa.getAtivo())
                    .fase(etapa.getFase())
                    .projeto(ProjetoRepresentation.Padrao.from(etapa.getProjeto()))
                    .build();
        }
    }

    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;

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

        private ProjetoRepresentation.Padrao projeto;


        private static EtapaRepresentation.Lista from(Etapa etapa) {
            return Lista.builder()
                    .id(etapa.getId())
                    .nome(etapa.getNome())
                    .descricao(etapa.getDescricao())
                    .data_previsao_inicio(etapa.getData_previsao_inicio())
                    .data_inicio(etapa.getData_inicio())
                    .data_previsao_fim(etapa.getData_previsao_fim())
                    .data_fim(etapa.getData_fim())
                    .custo(etapa.getCusto())
                    .status(etapa.getStatus())
                    .ativo(etapa.getAtivo())
                    .fase(etapa.getFase())
                    .projeto(ProjetoRepresentation.Padrao.from(etapa.getProjeto()))
                    .build();
        }
        public static List<EtapaRepresentation.Lista> from(List<Etapa> etapaList) {
            return etapaList
                    .stream()
                    .map(EtapaRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Padrao {
        private Long id;

        private String nome;

        private Status status;

        private String descricao;

        private LocalDateTime data_previsao_inicio;

        private LocalDateTime data_inicio;

        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;

        private Fase fase;

        public static Padrao from(Etapa etapa) {
            return Padrao.builder()
                    .id(etapa.getId())
                    .nome(etapa.getNome())
                    .status(etapa.getStatus())
                    .descricao(etapa.getDescricao())
                    .data_previsao_inicio(etapa.getData_previsao_inicio())
                    .data_inicio(etapa.getData_inicio())
                    .data_previsao_fim(etapa.getData_previsao_fim())
                    .data_fim(etapa.getData_fim())
                    .fase(etapa.getFase())
                    .build();
        }

        public static List<Padrao> from(List<Etapa> etapaList) {
            return etapaList.stream()
                    .map(Padrao::from)
                    .collect(Collectors.toList());
        }

    }
}