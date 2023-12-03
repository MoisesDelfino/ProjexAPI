package com.projexapi.projexapi.Comentario;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Projeto.ProjetoRepresentation;
import com.projexapi.projexapi.Setor.Setor;
import com.projexapi.projexapi.Setor.SetorRepresentation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface ComentarioRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar{

        @NotNull(message = "O setor nome não pode ser nulo")
        private Setor setor;

        @NotNull(message = "O campo conteudo não pode ser nulo")
        private String conteudo;

        @NotNull(message = "O campo data_inclusao não pode ser nulo")
        private LocalDateTime data_inclusao;

        private Long id_comentario_pai;


    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar{

        @NotNull(message = "O setor nome não pode ser nulo")
        private Setor setor;

        @NotNull(message = "O campo conteudo não pode ser nulo")
        private String conteudo;

        @NotNull(message = "O campo data_inclusao não pode ser nulo")
        private LocalDateTime data_inclusao;

        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

        private Long id_comentario_pai;


    }
    @Data
    @Builder
    class Detalhes {
        private Long id;

        private Setor setor;

        private String conteudo;

        private LocalDateTime data_inclusao;

        private ProjetoRepresentation.Padrao projeto;


        private Ativo ativo;

        private Long id_comentario_pai;



        public static ComentarioRepresentation.Detalhes from(Comentario comentario) {
            return Detalhes.builder()
                    .id(comentario.getId())
                    .setor(comentario.getSetor())
                    .conteudo(comentario.getConteudo())
                    .data_inclusao(comentario.getData_inclusao())
                    .projeto(ProjetoRepresentation.Padrao.from(comentario.getProjeto()))
                    .ativo(comentario.getAtivo())
                    .id_comentario_pai(comentario.getId_comentario_pai())
                    .build();
        }
        public static List<ComentarioRepresentation.Detalhes> from(List<Comentario> ComentarioList) {
            return ComentarioList
                    .stream()
                    .map(ComentarioRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Lista {
        private Long id;

        private SetorRepresentation.Padrao setor;

        private String conteudo;

        private LocalDateTime data_inclusao;

        private ProjetoRepresentation.Padrao projeto;

        private Ativo ativo;

        private Long id_comentario_pai;



        private static ComentarioRepresentation.Lista from(Comentario comentario) {
            return Lista.builder()
                    .id(comentario.getId())
                    .setor(SetorRepresentation.Padrao.from(comentario.getSetor()))
                    .conteudo(comentario.getConteudo())
                    .data_inclusao(comentario.getData_inclusao())
                    .projeto(ProjetoRepresentation.Padrao.from(comentario.getProjeto()))
                    .ativo(comentario.getAtivo())
                    .id_comentario_pai(comentario.getId_comentario_pai())
                    .build();
        }
        public static List<ComentarioRepresentation.Lista> from(List<Comentario> ComentarioList) {
            return ComentarioList
                    .stream()
                    .map(ComentarioRepresentation.Lista::from)
                    .collect(Collectors.toList());
        }
    }
    @Data
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class Padrao {
        private Long id;

        private SetorRepresentation.Padrao setor;

        private String conteudo;

        private LocalDateTime data_inclusao;

       private Ativo ativo;

        private Long id_comentario_pai;



        private static ComentarioRepresentation.Padrao from(Comentario comentario) {
            return Padrao.builder()
                    .id(comentario.getId())
                    .setor(SetorRepresentation.Padrao.from(comentario.getSetor()))
                    .conteudo(comentario.getConteudo())
                    .data_inclusao(comentario.getData_inclusao())
                    .ativo(comentario.getAtivo())
                    .id_comentario_pai(comentario.getId_comentario_pai())
                    .build();
        }
        public static List<ComentarioRepresentation.Padrao> from(List<Comentario> ComentarioList) {
            return ComentarioList
                    .stream()
                    .map(ComentarioRepresentation.Padrao::from)
                    .collect(Collectors.toList());
        }
    }
}
