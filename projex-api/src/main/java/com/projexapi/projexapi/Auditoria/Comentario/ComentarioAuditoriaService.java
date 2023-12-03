package com.projexapi.projexapi.Auditoria.Comentario;

import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class ComentarioAuditoriaService {
    private ComentarioAuditoriaRepository comentarioAuditoriaRepository;

    public Page<ComentarioAuditoria> buscarTodos(Pageable pageable) {
        return this.comentarioAuditoriaRepository.findAll(pageable);
    }

    public Page<ComentarioAuditoria> buscarTodos(Predicate filtroURI, Pageable pageable) {
        return this.comentarioAuditoriaRepository.findAll(filtroURI, pageable);

    }

    public ComentarioAuditoria criarComentarioAuditoria(ComentarioAuditoriaRepresentation.Criar criar) {


        ComentarioAuditoria comentarioAuditoriaCriado = this.comentarioAuditoriaRepository.save(ComentarioAuditoria.builder()
                .id_comentario(criar.getId_comentario())
                .conteudo(criar.getConteudo())
                .data_inclusao(criar.getData_inclusao())
                .ativo(criar.getAtivo())
                .usuario(criar.getUsuario())
                .data_hora((criar.getData_hora()))
                .tipo_operacao(criar.getTipo_operacao())
                .build());


        return comentarioAuditoriaCriado;
    }
}
