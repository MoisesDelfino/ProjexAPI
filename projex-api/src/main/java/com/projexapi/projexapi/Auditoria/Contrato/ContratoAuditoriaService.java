package com.projexapi.projexapi.Auditoria.Contrato;


import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ContratoAuditoriaService {
    private ContratoAuditoriaRepository contratoAuditoriaRepository;

    public Page<ContratoAuditoria> buscarTodos(Pageable pageable) {
        return this.contratoAuditoriaRepository.findAll(pageable);
    }

    public Page<ContratoAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.contratoAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public ContratoAuditoria criarContratoAuditoria(ContratoAuditoriaRepresentation.Criar criar) {


        ContratoAuditoria contratoAuditoriaCriado = this.contratoAuditoriaRepository.save(ContratoAuditoria.builder()
                .id_contrato(criar.getId_contrato())
                .data_vigencia(criar.getData_vigencia())
                .razao_social(criar.getRazao_social())
                .numero_contrato(criar.getNumero_contrato())
                .objeto(criar.getObjeto())
                .valor_inicial(criar.getValor_inicial())
                .valor_final(criar.getValor_final())
                .ativo(criar.getAtivo())
                .usuario(criar.getUsuario())
                .data_hora((criar.getData_hora()))
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return contratoAuditoriaCriado;
    }
}
