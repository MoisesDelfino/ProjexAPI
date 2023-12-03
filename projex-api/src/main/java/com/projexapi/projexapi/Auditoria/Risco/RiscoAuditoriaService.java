package com.projexapi.projexapi.Auditoria.Risco;


import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class RiscoAuditoriaService {
    private RiscoAuditoriaRepository riscoAuditoriaRepository;

    public Page<RiscoAuditoria> buscarTodos(Pageable pageable) {
        return this.riscoAuditoriaRepository.findAll(pageable);
    }

    public Page<RiscoAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.riscoAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public RiscoAuditoria criarRiscoAuditoria(RiscoAuditoriaRepresentation.Criar criar) {


        RiscoAuditoria riscoAuditoriaCriado = this.riscoAuditoriaRepository.save(RiscoAuditoria.builder()
                .id_risco(criar.getId_risco())
                .nome(criar.getNome())
                .descricao(criar.getDescricao())
                .nivel(criar.getNivel())
                .ativo(criar.getAtivo())
                .usuario(criar.getUsuario())
                .data_hora((criar.getData_hora()))
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return riscoAuditoriaCriado;
    }
}
