package com.projexapi.projexapi.Auditoria.Projeto;

import com.projexapi.projexapi.Etapa.Etapa;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Enums.TipoOperacao;
import com.projexapi.projexapi.Risco.Risco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public interface ProjetoAuditoriaRepresentation {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar {

        @NotNull(message = "O campo id_projeto não pode ser nulo")
        private Long id_projeto;
        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        @NotNull(message = "O campo descricao não pode ser nulo")
        private String descricao;

        @NotNull(message = "O campo justificativa não pode ser nulo")
        private String justificativa;

        @NotNull(message = "O campo objetivo não pode ser nulo")
        private String objetivo;

        @NotNull(message = "O campo observacao não pode ser nulo")
        private String observacao;

        @NotNull(message = "O campo orcamento_inicial não pode ser nulo")
        private BigDecimal orcamento_inicial;

        @NotNull(message = "O campo custo_real não pode ser nulo")
        private BigDecimal custo_real;

        @NotNull(message = "O campo status não pode ser nulo")
        private Status status;

        @NotNull(message = "O campo stakeholders não pode ser nulo")
        private String stakeholders;

        @NotNull(message = "O campo beneficios não pode ser nulo")
        private String beneficios;

        @NotNull(message = "O campo premissa não pode ser nulo")
        private String premissa;

        @NotNull(message = "O campo restricao não pode ser nulo")
        private String restricao;

        @NotNull(message = "O campo setor não pode ser nulo")
        private String setor;

        @NotNull(message = "O campo responsavel não pode ser nulo")
        private String responsavel;

        private List<Etapa> etapaList;

        private List<Risco> riscoList;

        private Double percentual_conclusao;

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

        @NotNull(message = "O campo id_projeto não pode ser nulo")
        private Long id_projeto;

        @NotNull(message = "O campo nome não pode ser nulo")
        @NotEmpty(message = "O campo nome não pode ser vazio")
        private String nome;

        @NotNull(message = "O campo descricao não pode ser nulo")
        private String descricao;

        @NotNull(message = "O campo justificativa não pode ser nulo")
        private String justificativa;

        @NotNull(message = "O campo objetivo não pode ser nulo")
        private String objetivo;

        @NotNull(message = "O campo observacao não pode ser nulo")
        private String observacao;

        @NotNull(message = "O campo orcamento_inicial não pode ser nulo")
        private BigDecimal orcamento_inicial;

        @NotNull(message = "O campo custo_real não pode ser nulo")
        private BigDecimal custo_real;

        @NotNull(message = "O campo status não pode ser nulo")
        private Status status;

        @NotNull(message = "O campo stakeholders não pode ser nulo")
        private String stakeholders;

        @NotNull(message = "O campo beneficios não pode ser nulo")
        private String beneficios;

        @NotNull(message = "O campo premissa não pode ser nulo")
        private String premissa;

        @NotNull(message = "O campo restricao não pode ser nulo")
        private String restricao;

        @NotNull(message = "O campo setor não pode ser nulo")
        private String setor;

        @NotNull(message = "O campo responsavel não pode ser nulo")
        private String responsavel;

        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;

        private Double percentual_conslusao;

        @NotNull(message = "O campo usuario não pode ser nulo")
        private String usuario;

        @NotNull(message = "O campo data_hora não pode ser nulo")
        private Timestamp data_hora;

        @NotNull(message = "O campo tipo_operacao não pode ser nulo")
        private TipoOperacao tipo_operacao;


        public static Detalhes from(ProjetoAuditoria projetoAuditoria) {
            return Detalhes.builder()
                    .id_projeto(projetoAuditoria.getId_projeto())
                    .nome(projetoAuditoria.getNome())
                    .descricao(projetoAuditoria.getDescricao())
                    .justificativa(projetoAuditoria.getJustificativa())
                    .objetivo(projetoAuditoria.getObjetivo())
                    .observacao(projetoAuditoria.getObservacao())
                    .orcamento_inicial(projetoAuditoria.getOrcamento_inicial())
                    .custo_real(projetoAuditoria.getCusto_real())
                    .status(projetoAuditoria.getStatus())
                    .stakeholders(projetoAuditoria.getStakeholders())
                    .beneficios(projetoAuditoria.getBeneficios())
                    .premissa(projetoAuditoria.getPremissa())
                    .restricao(projetoAuditoria.getRestricao())
                    .setor(projetoAuditoria.getSetor())
                    .responsavel(projetoAuditoria.getResponsavel())
                    .ativo(projetoAuditoria.getAtivo())
                    .usuario(projetoAuditoria.getUsuario())
                    .data_hora(projetoAuditoria.getData_hora())
                    .tipo_operacao(projetoAuditoria.getTipo_operacao())
                    .percentual_conslusao(projetoAuditoria.getPercentual_conslusao())
                    .build();
        }
        public static List<Detalhes> from(List<ProjetoAuditoria> projetoList) {
            return projetoList.stream()
                    .map(Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}
