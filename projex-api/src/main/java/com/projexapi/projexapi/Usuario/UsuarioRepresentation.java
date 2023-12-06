package com.projexapi.projexapi.Usuario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Auth.Models.Role;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Setor.SetorRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface UsuarioRepresentation {

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

        private SetorRepresentation.Padrao setor;

        private Set<Role> roles;

        private Ativo ativo;

        private List<String> token_dispositivo;



        public static com.projexapi.projexapi.Usuario.UsuarioRepresentation.Detalhes from(Usuario usuario) {
            return com.projexapi.projexapi.Usuario.UsuarioRepresentation.Detalhes.builder()
                    .id(usuario.getId())
                    .username(usuario.getUsername())
                    .email(usuario.getEmail())
                    .password(usuario.getPassword())
                    .roles(usuario.getRoles())
                    .ativo(usuario.getAtivo())
                    .token_dispositivo(usuario.getTokensDispositivo())
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

        private List<String> token_dispositivo;


        private static com.projexapi.projexapi.Usuario.UsuarioRepresentation.Lista from(Usuario usuario) {
            return com.projexapi.projexapi.Usuario.UsuarioRepresentation.Lista.builder()
                    .id(usuario.getId())
                    .username(usuario.getUsername())
                    .email(usuario.getEmail())
                    .roles(usuario.getRoles())
                    .ativo(usuario.getAtivo())
                    .token_dispositivo(usuario.getTokensDispositivo())
                    .build();
        }

        public static List<com.projexapi.projexapi.Usuario.UsuarioRepresentation.Lista> from(List<Usuario> usuarioList) {
            return usuarioList
                    .stream()
                    .map(com.projexapi.projexapi.Usuario.UsuarioRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
    @Builder
    @Data
    class Padrao {
        private Long id;
        private String username;

        public static com.projexapi.projexapi.Usuario.UsuarioRepresentation.Padrao from(Usuario usuario) {
            return com.projexapi.projexapi.Usuario.UsuarioRepresentation.Padrao.builder()
                    .id(usuario.getId())
                    .username(usuario.getUsername())
                    .build();
        }

        public static List<com.projexapi.projexapi.Usuario.UsuarioRepresentation.Padrao> from(List<Usuario> projetoList) {
            return projetoList.stream()
                    .map(com.projexapi.projexapi.Usuario.UsuarioRepresentation.Padrao::from)
                    .collect(Collectors.toList());
        }

    }
}
