package com.projexapi.projexapi.Setor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Auth.Models.Role;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public interface SetorRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {
        @NotNull(message = "O campo username não pode ser nulo")
        @NotEmpty(message = "O campo username não pode ser vazio")
        private String username;

        private String email;

        private String password;

        private Set<Role> roles;

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar {
        @NotNull(message = "O campo username não pode ser nulo")
        @NotEmpty(message = "O campo username não pode ser vazio")
        private String username;

        private String email;

        private Set<Role> roles;

        private Ativo ativo;


    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class AtualizarDeviceToken {

        private String token_dispositivo;

    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Detalhes {
        private Long id;

        @NotNull(message = "O campo username não pode ser nulo")
        @NotEmpty(message = "O campo username não pode ser vazio")
        private String username;

        private String email;

        private String password;


        private Set<Role> roles;

        private Ativo ativo;

        private List<String> token_dispositivo;



        public static SetorRepresentation.Detalhes from(Setor setor) {
            return Detalhes.builder()
                            .id(setor.getId())
                            .username(setor.getUsername())
                            .email(setor.getEmail())
                            .password(setor.getPassword())
                            .roles(setor.getRoles())
                            .ativo(setor.getAtivo())
                            .token_dispositivo(setor.getTokensDispositivo())
                            .build();
        }

    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;

        private String username;

        private String email;

        private String password;

        private Set<Role> roles;

        private Ativo ativo;

        private List<ProjetoRepresentation.Padrao> projetos;

        private List<String> token_dispositivo;


        private static SetorRepresentation.Lista from(Setor setor) {
            return Lista.builder()
                    .id(setor.getId())
                    .username(setor.getUsername())
                    .email(setor.getEmail())
                    .roles(setor.getRoles())
                    .ativo(setor.getAtivo())
                    .projetos(ProjetoRepresentation.Padrao.from(setor.getProjetoList()))
                    .token_dispositivo(setor.getTokensDispositivo())
                    .build();
        }

        public static List<SetorRepresentation.Lista> from(List<Setor> setorList) {
            return setorList
                    .stream()
                    .map(SetorRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
    @Builder
    @Data
    class Padrao {
        private Long id;
        private String username;

        public static Padrao from(Setor setor) {
            return Padrao.builder()
                    .id(setor.getId())
                    .username(setor.getUsername())
                    .build();
        }

        public static List<Padrao> from(List<Setor> projetoList) {
            return projetoList.stream()
                    .map(SetorRepresentation.Padrao::from)
                    .collect(Collectors.toList());
        }

    }
}
