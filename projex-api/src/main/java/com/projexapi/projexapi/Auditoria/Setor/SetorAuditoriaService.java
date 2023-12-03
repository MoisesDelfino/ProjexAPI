package com.projexapi.projexapi.Auditoria.Setor;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SetorAuditoriaService {
    private SetorAuditoriaRepository setorAuditoriaRepository;

    public Page<SetorAuditoria> buscarTodos(Pageable pageable) {
        return this.setorAuditoriaRepository.findAll(pageable);
    }

    public Page<SetorAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.setorAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public SetorAuditoria criarSetorAuditoria(SetorAuditoriaRepresentation.Criar criar) {


        SetorAuditoria setorAuditoriaCriado = this.setorAuditoriaRepository.save(SetorAuditoria.builder()
                .id_setor(criar.getId_setor())
                .username(criar.getUsername())
                .email(criar.getEmail())
                .password(criar.getPassword())
                .administrador(criar.getAdministrador())
               // .gerente(criar.getGerente())
                .ativo(criar.getAtivo())
                .usuario(criar.getUsuario())
                .data_hora((criar.getData_hora()))
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return setorAuditoriaCriado;
    }
}
