package com.projexapi.projexapi.Responsavel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Enums.Ativo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public interface ResponsavelRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        private String telefone;

        private String email;

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar {

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        private String telefone;

        private String email;

        private Ativo ativo;

    }

    @Data
    @Builder
    class Detalhes {
        private Long id;

        private String nome;

        private String telefone;

        private String email;

        private Ativo ativo;

        public static ResponsavelRepresentation.Detalhes from(Responsavel responsavel) {
            return Detalhes.builder()
                    .id(responsavel.getId())
                    .nome(responsavel.getNome())
                    .telefone(responsavel.getTelefone())
                    .email(responsavel.getEmail())
                    .ativo(responsavel.getAtivo())
                    .build();
        }
    }
    @Builder
    @Data
    class Padrao {
        private Long id;
        private String nome;

        public static ResponsavelRepresentation.Padrao from(Responsavel responsavel) {
            return ResponsavelRepresentation.Padrao.builder()
                    .id(responsavel.getId())
                    .nome(responsavel.getNome())
                    .build();
        }

        public static List<ResponsavelRepresentation.Padrao> from(List<Responsavel> responsavelList) {
            return responsavelList.stream()
                    .map(ResponsavelRepresentation.Padrao::from)
                    .collect(Collectors.toList());
        }

    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;

        private String nome;

        private String telefone;

        private String email;

        private Ativo ativo;

        private static ResponsavelRepresentation.Lista from(Responsavel responsavel) {
            return Lista.builder()
                    .id(responsavel.getId())
                    .nome(responsavel.getNome())
                    .telefone(responsavel.getTelefone())
                    .email(responsavel.getEmail())
                    .ativo(responsavel.getAtivo())
                    .build();
        }
        public static List<ResponsavelRepresentation.Lista> from(List<Responsavel> responsavelList) {
            return responsavelList
                    .stream()
                    .map(ResponsavelRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
}
