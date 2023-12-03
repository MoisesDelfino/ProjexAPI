package com.projexapi.projexapi.Auditoria.Acao;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class AcaoAuditoriaService {
    private AcaoAuditoriaRepository acaoAuditoriaRepository;

    public Page<AcaoAuditoria> buscarTodos(Pageable pageable) {
        return this.acaoAuditoriaRepository.findAll(pageable);
    }

    public Page<AcaoAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.acaoAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public AcaoAuditoria criarAcaoAuditoria(AcaoAuditoriaRepresentation.Criar criar) {


        AcaoAuditoria acaoAuditoriaCriado = this.acaoAuditoriaRepository.save(AcaoAuditoria.builder()
                .id_acao(criar.getId_acao())
                .nome(criar.getNome())
                .descricao(criar.getDescricao())
                .data_previsao_inicio(criar.getData_previsao_inicio())
                .data_inicio(LocalDateTime.parse("1900-01-01T00:00:00"))
                .data_previsao_fim(criar.getData_previsao_fim())
                .data_fim(LocalDateTime.parse("1900-01-01T00:00:00"))
                .custo(criar.getCusto())
                .status(criar.getStatus())
                .ativo(criar.getAtivo())
                .usuario(criar.getUsuario())
                .data_hora((criar.getData_hora()))
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return acaoAuditoriaCriado;
    }
}
