package com.projexapi.projexapi.Auditoria.Comentario;

import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoOperacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface ComentarioAuditoriaRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar{
        @NotNull(message = "O id_comentario nome não pode ser nulo")
        private Long id_comentario;

        private Long id_comentario_pai;

        @NotNull(message = "O campo conteudo não pode ser nulo")
        private String conteudo;

        @NotNull(message = "O campo data_inclusao não pode ser nulo")
        private LocalDateTime data_inclusao;

       private Ativo Ativo;

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

        private Long id_comentario;


        private String conteudo;

        private LocalDateTime data_inclusao;

        private Ativo ativo;

        private Long id_comentario_pai;

        private String usuario;

        private Timestamp data_hora;

        private TipoOperacao tipo_operacao;



        public static ComentarioAuditoriaRepresentation.Detalhes from(ComentarioAuditoria comentarioAuditoria) {
            return Detalhes.builder()
                    .id(comentarioAuditoria.getId())
                    .id_comentario(comentarioAuditoria.getId_comentario())
                    .conteudo(comentarioAuditoria.getConteudo())
                    .data_inclusao(comentarioAuditoria.getData_inclusao())
                    .ativo(comentarioAuditoria.getAtivo())
                    .id_comentario_pai(comentarioAuditoria.getId_comentario_pai())
                    .usuario(comentarioAuditoria.getUsuario())
                    .data_hora(comentarioAuditoria.getData_hora())
                    .tipo_operacao(comentarioAuditoria.getTipo_operacao())
                    .build();
        }
        public static List<ComentarioAuditoriaRepresentation.Detalhes> from(List<ComentarioAuditoria> comentarioList) {
            return comentarioList.stream()
                    .map(ComentarioAuditoriaRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
    }

    }
}
