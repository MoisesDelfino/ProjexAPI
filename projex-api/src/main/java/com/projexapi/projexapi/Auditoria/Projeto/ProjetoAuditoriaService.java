package com.projexapi.projexapi.Auditoria.Projeto;

import com.projexapi.projexapi.Projeto.Projeto;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ProjetoAuditoriaService {

    private ProjetoAuditoriaRepository projetoAuditoriaRepository;

    public Page<ProjetoAuditoria> buscarTodos(Pageable pageable) {
        return this.projetoAuditoriaRepository.findAll(pageable);
    }

    public Page<ProjetoAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.projetoAuditoriaRepository.findAll(filtroURI, pageable);
    }

    public ProjetoAuditoria criarProjetoAuditoria(ProjetoAuditoriaRepresentation.Criar criar) {


        ProjetoAuditoria projetoAuditoriaCriado = this.projetoAuditoriaRepository.save(ProjetoAuditoria.builder()
                .id_projeto(criar.getId_projeto())
                .nome(criar.getNome())
                .descricao(criar.getDescricao())
                .justificativa(criar.getJustificativa())
                .objetivo(criar.getObjetivo())
                .observacao(criar.getObservacao())
                .orcamento_inicial(criar.getOrcamento_inicial())
                .custo_real(criar.getCusto_real())
                .status(criar.getStatus())
                .stakeholders(criar.getStakeholders())
                .beneficios(criar.getBeneficios())
                .premissa(criar.getPremissa())
                .restricao(criar.getRestricao())
                .setor(criar.getSetor())
                .responsavel(criar.getResponsavel())
                .ativo(criar.getAtivo())
                .percentual_conslusao(criar.getPercentual_conclusao())
                .usuario(criar.getUsuario())
                .data_hora(criar.getData_hora())
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return projetoAuditoriaCriado;
    }
}
