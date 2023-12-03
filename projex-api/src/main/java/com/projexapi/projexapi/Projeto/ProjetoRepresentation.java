package com.projexapi.projexapi.Projeto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.projexapi.projexapi.Etapa.EtapaRepresentation;
import com.projexapi.projexapi.Comentario.ComentarioRepresentation;
import com.projexapi.projexapi.Contrato.ContratoRepresentation;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.Status;
import com.projexapi.projexapi.Responsavel.Responsavel;
import com.projexapi.projexapi.Responsavel.ResponsavelRepresentation;
import com.projexapi.projexapi.Risco.Risco;
import com.projexapi.projexapi.Risco.RiscoRepresentation;
import com.projexapi.projexapi.Setor.SetorRepresentation;
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

public interface ProjetoRepresentation {

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

        @NotNull(message = "O campo responsavel não pode ser nulo")
        private Responsavel responsavel;

        @NotNull(message = "O campo dias_prazo_finalizacao não pode ser nulo")
        private Integer dias_prazo_finalizacao;

        private List<String> arquivos;
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

        @NotNull(message = "O campo justificativa não pode ser nulo")
        private String justificativa;

        @NotNull(message = "O campo objetivo não pode ser nulo")
        private String objetivo;

        @NotNull(message = "O campo observacao não pode ser nulo")
        private String observacao;

        @NotNull(message = "O campo data_previsao_inicio não pode ser nulo")
        private LocalDateTime data_previsao_inicio;

        @NotNull(message = "O campo data_inicio não pode ser nulo")
        private LocalDateTime data_inicio;

        @NotNull(message = "O campo data_previsao_fim não pode ser nulo")
        private LocalDateTime data_previsao_fim;

        @NotNull(message = "O campo data_fim não pode ser nulo")
        private LocalDateTime data_fim;

        @NotNull(message = "O campo custo_previsto não pode ser nulo")
        private BigDecimal custo_previsto;

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

        @NotNull(message = "O campo responsavel não pode ser nulo")
        private Responsavel responsavel;

        @NotNull(message = "O campo dias_prazo_finalizacao não pode ser nulo")
        private int dias_prazo_finalizacao;

        private List<Risco> riscoList;

        private List<String> arquivos;

    }

    @Data
    @Builder
    class Detalhes {
        private Long id;

        private String nome;

        private String descricao;

        private String justificativa;

        private String objetivo;

        private String observacao;

        private LocalDateTime data_previsao_inicio;

        private LocalDateTime data_inicio;

        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;

        private BigDecimal orcamento_inicial;

        private BigDecimal custo_previsto;

        private BigDecimal custo_real;

        private Status status;

        private String stakeholders;

        private String beneficios;

        private String premissa;

        private String restricao;

        private int dias_prazo_finalizacao;

        private Ativo ativo;

        private SetorRepresentation.Padrao setor;

        private List<EtapaRepresentation.Padrao> acoes;

        private List<RiscoRepresentation.Detalhes> riscos;

        private List<ContratoRepresentation.Padrao> contratos;

        private List<ComentarioRepresentation.Padrao> comentarios;

        private ResponsavelRepresentation.Padrao responsavel;

        private List<String> arquivos;

        private BigDecimal percentual_conclusao;

        private Long sequencial;

        public static Detalhes from(Projeto projeto) {
            return Detalhes.builder()
                            .id(projeto.getId())
                            .nome(projeto.getNome())
                            .descricao(projeto.getDescricao())
                            .justificativa(projeto.getJustificativa())
                            .objetivo(projeto.getObjetivo())
                            .data_previsao_inicio(projeto.getData_previsao_inicio())
                            .data_inicio(projeto.getData_inicio())
                            .data_previsao_fim(projeto.getData_previsao_fim())
                            .data_fim(projeto.getData_fim())
                            .orcamento_inicial(projeto.getOrcamento_inicial())
                            .custo_previsto(projeto.getCusto_previsto())
                            .custo_real(projeto.getCusto_real())
                            .status(projeto.getStatus())
                            .stakeholders(projeto.getStakeholders())
                            .beneficios(projeto.getBeneficios())
                            .premissa(projeto.getPremissa())
                            .restricao(projeto.getRestricao())
                            .dias_prazo_finalizacao(projeto.getDias_prazo_finalizacao())
                            .setor(SetorRepresentation.Padrao.from(projeto.getSetor()))
                            .ativo(projeto.getAtivo())
                            .responsavel(ResponsavelRepresentation.Padrao.from(projeto.getResponsavel()))
                            .acoes(EtapaRepresentation.Padrao.from(projeto.getEtapaList()))
                            .riscos(RiscoRepresentation.Detalhes.from(projeto.getRiscoList()))
                            .contratos(ContratoRepresentation.Padrao.from(projeto.getContratoList()))
                            .comentarios(ComentarioRepresentation.Padrao.from(projeto.getComentarioList()))
                            .arquivos(projeto.getArquivos())
                            .percentual_conclusao(projeto.getPercentual_conclusao())
                            .sequencial(projeto.getSequencial())
                    .build();
        }

        public static List<Detalhes> from(List<Projeto> ProjetoList) {
            return ProjetoList
                    .stream()
                    .map(Detalhes::from)
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

        private String justificativa;

        private String objetivo;

        private String observacao;

        private LocalDateTime data_previsao_inicio;

        private LocalDateTime data_inicio;

        private LocalDateTime data_previsao_fim;

        private LocalDateTime data_fim;

        private BigDecimal orcamento_inicial;

        private BigDecimal custo_previsto;

        private BigDecimal custo_real;

        private Status status;

        private String stakeholders;

        private String beneficios;

        private String premissa;

        private String restricao;

        private Ativo ativo;

        private ResponsavelRepresentation.Padrao responsavel;

        private int dias_prazo_finalizacao;

        private SetorRepresentation.Padrao setor;

        private List<EtapaRepresentation.Padrao> acoes;

        private List<RiscoRepresentation.Detalhes> riscos;

        private List<ContratoRepresentation.Padrao> contratos;

        private List<ComentarioRepresentation.Padrao> comentarios;

        private List<String> arquivos;

        private BigDecimal percentual_conclusao;

        private Long sequencial;



        private static ProjetoRepresentation.Lista from(Projeto projeto) {
            return Lista.builder()
                    .id(projeto.getId())
                    .nome(projeto.getNome())
                    .descricao(projeto.getDescricao())
                    .justificativa(projeto.getJustificativa())
                    .objetivo(projeto.getObjetivo())
                    .observacao(projeto.getObservacao())
                    .data_previsao_inicio(projeto.getData_previsao_fim())
                    .data_inicio(projeto.getData_inicio())
                    .data_previsao_fim(projeto.getData_previsao_fim())
                    .data_fim(projeto.getData_fim())
                    .orcamento_inicial(projeto.getOrcamento_inicial())
                    .custo_previsto(projeto.getCusto_previsto())
                    .custo_real(projeto.getCusto_real())
                    .status(projeto.getStatus())
                    .stakeholders(projeto.getStakeholders())
                    .beneficios(projeto.getBeneficios())
                    .premissa(projeto.getPremissa())
                    .restricao(projeto.getRestricao())
                    .dias_prazo_finalizacao((projeto.getDias_prazo_finalizacao()))
                    .setor(SetorRepresentation.Padrao.from(projeto.getSetor()))
                    .acoes(EtapaRepresentation.Padrao.from(projeto.getEtapaList()))
                    .ativo(projeto.getAtivo())
                    .responsavel(ResponsavelRepresentation.Padrao.from(projeto.getResponsavel()))
                    .riscos(RiscoRepresentation.Detalhes.from(projeto.getRiscoList()))
                    .contratos(ContratoRepresentation.Padrao.from(projeto.getContratoList()))
                    .comentarios(ComentarioRepresentation.Padrao.from(projeto.getComentarioList()))
                    .arquivos(projeto.getArquivos())
                    .percentual_conclusao(projeto.getPercentual_conclusao())
                    .sequencial(projeto.getSequencial())
                    .build();
        }

        public static List<Lista> from(List<Projeto> ProjetoList) {
            return ProjetoList
                    .stream()
                    .map(Lista::from)
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

        private Ativo ativo;

        private Status status;


        public static Padrao from(Projeto projeto) {
            return Padrao.builder()
                    .id(projeto.getId())
                    .nome(projeto.getNome())
                    .ativo(projeto.getAtivo())
                    .status(projeto.getStatus())
                    .build();
        }

        public static List<Padrao> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(Padrao::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaPrincipal {

        private String nome;

        private String descricao;

        private String justificativa;

        private String objetivo;



        public static AbaPrincipal from(Projeto projeto) {
            return AbaPrincipal.builder()
                    .nome(projeto.getNome())
                    .descricao(projeto.getDescricao())
                    .justificativa(projeto.getJustificativa())
                    .objetivo(projeto.getObjetivo())
                    .build();
        }

        public static List<AbaPrincipal> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaPrincipal::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaDetalhes {

        private String premissa;

        private String restricoes;

        private String beneficios;

        private BigDecimal orcamento_inicial;

        private BigDecimal custo_previsto;

        private BigDecimal custo_real;

        private List<String> arquivos;

        private int dias_prazo_finalizacao;



        public static AbaDetalhes from(Projeto projeto) {
            return AbaDetalhes.builder()
                    .premissa(projeto.getPremissa())
                    .restricoes(projeto.getRestricao())
                    .beneficios(projeto.getBeneficios())
                    .orcamento_inicial(projeto.getOrcamento_inicial())
                    .custo_previsto(projeto.getCusto_previsto())
                    .custo_real(projeto.getCusto_real())
                    .arquivos(projeto.getArquivos())
                    .dias_prazo_finalizacao(projeto.getDias_prazo_finalizacao())
                    .build();
        }

        public static List<AbaDetalhes> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaDetalhes::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaResponsavel {

        private ResponsavelRepresentation.Padrao responsavel;

        private String stakeholders;

        private String observacao;

        private SetorRepresentation.Padrao setor;

        public static AbaResponsavel from(Projeto projeto) {
            return AbaResponsavel.builder()
                    .responsavel(ResponsavelRepresentation.Padrao.from(projeto.getResponsavel()))
                    .stakeholders(projeto.getStakeholders())
                    .observacao(projeto.getObservacao())
                    .setor(SetorRepresentation.Padrao.from(projeto.getSetor()))
                    .build();
        }

        public static List<AbaResponsavel> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaResponsavel::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaAcoes {

        private List<EtapaRepresentation.Padrao> acoes;


        public static AbaAcoes from(Projeto projeto) {
            return AbaAcoes.builder()
                    .acoes(EtapaRepresentation.Padrao.from(projeto.getEtapaList()))
                    .build();
        }

        public static List<AbaAcoes> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaAcoes::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaRiscos {

        private List<RiscoRepresentation.Detalhes> riscos;


        public static AbaRiscos from(Projeto projeto) {
            return AbaRiscos.builder()
                    .riscos(RiscoRepresentation.Detalhes.from(projeto.getRiscoList()))
                    .build();
        }

        public static List<AbaRiscos> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaRiscos::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaContratos {

        private List<ContratoRepresentation.Padrao> contratos;


        public static AbaContratos from(Projeto projeto) {
            return AbaContratos.builder()
                    .contratos(ContratoRepresentation.Padrao.from(projeto.getContratoList()))
                    .build();
        }

        public static List<AbaRiscos> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaRiscos::from)
                    .collect(Collectors.toList());
        }

    }
    @Builder
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class AbaComentarios {

        private List<ComentarioRepresentation.Padrao> comentarios;


        public static AbaComentarios from(Projeto projeto) {
            return AbaComentarios.builder()
                    .comentarios(ComentarioRepresentation.Padrao.from(projeto.getComentarioList()))
                    .build();
        }

        public static List<AbaComentarios> from(List<Projeto> projetoList) {
            return projetoList.stream()
                    .map(AbaComentarios::from)
                    .collect(Collectors.toList());
        }

    }
}
