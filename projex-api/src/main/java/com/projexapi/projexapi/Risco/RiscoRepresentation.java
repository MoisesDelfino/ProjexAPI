package com.projexapi.projexapi.Risco;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.NivelRisco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface RiscoRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        private String descricao;

        private NivelRisco nivel;

        private List<Risco> riscoList;

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar {

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        private String descricao;

        private NivelRisco nivel;

        private Ativo ativo;

        private List<Risco> riscoList;

    }

    @Data
    @Builder
    class Detalhes {
        private Long id;

        private String nome;

        private String descricao;

        private NivelRisco nivel;

        private Ativo ativo;

        private List<Risco> riscoList;


        public static RiscoRepresentation.Detalhes from(Risco risco) {
            return Detalhes.builder()
                    .id(risco.getId())
                    .nome(risco.getNome())
                    .descricao(risco.getDescricao())
                    .nivel(risco.getNivel())
                    .ativo(risco.getAtivo())
                    .build();
        }
        public static List<RiscoRepresentation.Detalhes> from(List<Risco> RiscoList) {
            return RiscoList
                    .stream()
                    .map(RiscoRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }

    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;

        private String nome;

        private String descricao;

        private NivelRisco nivel;

        private Ativo ativo;


        private static  RiscoRepresentation.Lista from(Risco risco) {
            return Lista.builder()
                    .id(risco.getId())
                    .nome(risco.getNome())
                    .nivel(risco.getNivel())
                    .descricao(risco.getDescricao())
                    .ativo(risco.getAtivo())
                    .build();
        }

        public static List<RiscoRepresentation.Lista> from(List<Risco> RiscoList) {
            return RiscoList
                    .stream()
                    .map(RiscoRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
