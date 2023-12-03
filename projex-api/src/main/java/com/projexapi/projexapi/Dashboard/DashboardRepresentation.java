package com.projexapi.projexapi.Dashboard;

import com.projexapi.projexapi.Etapa.Etapa;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Projeto.Projeto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface DashboardRepresentation {
    @Data
    @Builder
    class DetalhesProjeto {
        private Long id;

        private String nome;

        private String descricao;

        private LocalDateTime data_previsao_inicio;
        private LocalDateTime data_inicio;

        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;
        private Status status;


        public static DashboardRepresentation.DetalhesProjeto from(Projeto projeto) {
            return DashboardRepresentation.DetalhesProjeto.builder()
                    .id(projeto.getId())
                    .nome(projeto.getNome())
                    .descricao(projeto.getDescricao())
                    .data_previsao_inicio(projeto.getData_previsao_inicio())
                    .data_inicio(projeto.getData_inicio())
                    .data_previsao_fim(projeto.getData_previsao_fim())
                    .data_fim(projeto.getData_fim())
                    .status(projeto.getStatus())
                    .build();
        }
        public static List<DashboardRepresentation.DetalhesProjeto> from(List<Projeto> ProjetoList) {
            return ProjetoList
                    .stream()
                    .map(DashboardRepresentation.DetalhesProjeto::from)
                    .collect(Collectors.toList());
        }
    }
    @Data
    @Builder
    class DetalhesEtapa {
        private Long id;

        private String nome;

        private String descricao;

        private LocalDateTime data_previsao_inicio;

        private LocalDateTime data_inicio;

        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;

        private Status status;


        private static DashboardRepresentation.DetalhesEtapa from(Etapa etapa) {
            return DashboardRepresentation.DetalhesEtapa.builder()
                    .id(etapa.getId())
                    .nome(etapa.getNome())
                    .descricao(etapa.getDescricao())
                    .data_previsao_inicio(etapa.getData_previsao_inicio())
                    .data_inicio(etapa.getData_inicio())
                    .data_previsao_fim(etapa.getData_previsao_fim())
                    .data_fim(etapa.getData_fim())
                    .status(etapa.getStatus())
                    .build();
        }
        public static List<DashboardRepresentation.DetalhesEtapa> from(List<Etapa> etapaList) {
            return etapaList
                    .stream()
                    .map(DashboardRepresentation.DetalhesEtapa::from)
                    .collect(Collectors.toList());
        }
    }
}
