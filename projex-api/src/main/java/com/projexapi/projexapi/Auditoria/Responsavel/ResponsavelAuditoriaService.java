package com.projexapi.projexapi.Auditoria.Responsavel;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ResponsavelAuditoriaService {
    private ResponsavelAuditoriaRepository responsavelAuditoriaRepository;

    public Page<ResponsavelAuditoria> buscarTodos(Pageable pageable) {
        return this.responsavelAuditoriaRepository.findAll(pageable);
    }

    public Page<ResponsavelAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.responsavelAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public ResponsavelAuditoria criarResponsavelAuditoria(ResponsavelAuditoriaRepresentation.Criar criar) {


        ResponsavelAuditoria responsavelAuditoriaCriado = this.responsavelAuditoriaRepository.save(ResponsavelAuditoria.builder()
                .id_responsavel(criar.getId_responsavel())
                .nome(criar.getNome())
                .telefone(criar.getTelefone())
                .email(criar.getEmail())
                .ativo(criar.getAtivo())
                .usuario(criar.getUsuario())
                .data_hora(criar.getData_hora())
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return responsavelAuditoriaCriado;
    }
}
