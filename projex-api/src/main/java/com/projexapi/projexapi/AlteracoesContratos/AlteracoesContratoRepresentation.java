package com.projexapi.projexapi.AlteracoesContratos;

import com.projexapi.projexapi.Contrato.ContratoRepresentation;
import com.projexapi.projexapi.Enums.Ativo;
import com.projexapi.projexapi.Enums.TipoAlteracaoContrato;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public interface AlteracoesContratoRepresentation {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Criar{

        @NotNull(message = "O valor nome não pode ser nulo")
        private BigDecimal valor;


        @NotNull(message = "O campo tipo não pode ser nulo")
        private TipoAlteracaoContrato tipo;

    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    class Atualizar{

        @NotNull(message = "O valor nome não pode ser nulo")
        private BigDecimal valor;

        @NotNull(message = "O campo tipo não pode ser nulo")
        private TipoAlteracaoContrato tipo;

        @NotNull(message = "O campo ativo não pode ser nulo")
        private Ativo ativo;
    }
    @Data
    @Builder
    class Detalhes {
        private Long id;

        private BigDecimal valor;

        private TipoAlteracaoContrato tipo;

        private ContratoRepresentation.Padrao contrato;

        private Ativo ativo;


        public static AlteracoesContratoRepresentation.Detalhes from(AlteracoesContratos alteracaoContratos) {
            return Detalhes.builder()
                    .id(alteracaoContratos.getId())
                    .valor(alteracaoContratos.getValor())
                    .tipo(alteracaoContratos.getTipo())
                    .contrato(ContratoRepresentation.Padrao.from(alteracaoContratos.getContrato()))
                    .ativo(alteracaoContratos.getAtivo())
                    .build();
        }
        public static List<AlteracoesContratoRepresentation.Detalhes> from(List<AlteracoesContratos> alteracaoContratosList) {
            return alteracaoContratosList
                    .stream()
                    .map(AlteracoesContratoRepresentation.Detalhes::from)
                    .collect(Collectors.toList());
        }
    }
}


